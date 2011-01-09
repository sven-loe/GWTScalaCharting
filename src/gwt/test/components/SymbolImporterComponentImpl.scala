/*This file is part of GWTScalaCharting

GWTScalaCharting is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License.

GWTScalaCharting is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>. */

package gwt.test.components

import gwt.test.annotations._
import java.net.URL
import scala.io.Source
import gwt.test.services.ComponentContext
import gwt.test.entities.jpa._
import gwt.test.persistence._
import javax.persistence._

trait SymbolImporterComponentImpl extends SymbolImporterComponent {
	
  @Logging
  class SymbolImporterImpl(val context: ComponentContext) extends SymbolImporter with JpaUtil {
	  
      def storeSymbols(symbols: List[Symbol]) : Long = {
        var symbolCount = 0
        val em = context.getEntityManager
        var tx = em.getTransaction
        tx.begin
        try{
        	symbols.foreach(symbol => {
        		var mySymbol: Symbol = null
        		try{
        			val sQuery = new SQuery(em.createQuery("select sym from Symbol sym where sym.symbol=:symbol").setParameter("symbol", symbol.symbol))
        			mySymbol = sQuery.getSingleResult[Symbol]                                                                                                                                                    
        		} catch {
        		  case ex: NoResultException => {
        		    em.persist(symbol)
        		  }        		  
        		}
        		if(mySymbol != null) {
	        		mySymbol.name = symbol.name
	        		mySymbol.value = symbol.value
	        		em.persist(mySymbol)
        		}
        		symbolCount += 1
        		if(symbolCount % 500 == 0) {
        		  tx.commit
        		  println("Number " + symbolCount)
        		  tx = em.getTransaction
        		  tx.begin
        		}
        	})
        } catch {
          case ex: Exception => {
            println("Persist symbols failed.");
            tx.rollback
            throw ex
          } 
        }
        tx.commit
        println("Symbols imported " + symbolCount)
        symbolCount
      }
  }
}
