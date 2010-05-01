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
import gwt.test.entities._
import gwt.test.persistence._
import javax.persistence._

trait SymbolImporterComponentImpl extends SymbolImporterComponent {
	
  @Logging
  class SymbolImporterImpl(val context: ComponentContext) extends SymbolImporter with JpaUtil {
	  
    def importSymbols : List[Symbol] = {
	  	var symbols = List[Symbol]()
	  	val urlStr = "http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=%1$s"
	  	val urlStrNyse = String.format(urlStr, "NYSE")
	  	val urlNyse = new URL(urlStrNyse)  
	    val sourceNyse = Source.fromURL(urlNyse)
	    sourceNyse.getLines.foreach(line => {
	    	val symbol = makeSymbol(line)	    	
	    	if(symbol.symbol != "Symbol")  {
	    		symbols = symbol :: symbols
	    	}
	    	symbols
	    })
	    val urlStrNasdaq = String.format(urlStr, "NASDAQ")
	    val urlNasdaq = new URL(urlStrNasdaq)
	  	val sourceNasdaq = Source.fromURL(urlNasdaq)
	  	sourceNasdaq.getLines.foreach(line => {
	  		val symbol = makeSymbol(line) 
	  		if(symbol.symbol != "Symbol")  {
	    		symbols = symbol :: symbols
	    	}
	    	symbols
        })
	  	symbols
      }
 
    private def makeSymbol(line: String) : Symbol = {
       val symbol = new Symbol
	   val myLine = line.replace("\"","")
	   val cols = myLine.split(",")
	   symbol.symbol = cols(0)
	   symbol.name = cols(1)
	   symbol.value = java.lang.Long.parseLong(cols(3))
	   symbol
    }  
    
      def storeSymbols(symbols: List[Symbol]) : Long = {
        val symbolCount = 0
        val em = context.getEntityManager
        var tx = em.getTransaction
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
        		mySymbol.name = symbol.name
        		mySymbol.value = symbol.value
        		em.persist(mySymbol)
        		tx.commit
        	})
        } catch {
          case ex: Exception => {
            println("Persist symbols failed.");
            tx.rollback
            throw ex
          } 
        }
        symbolCount
      }
  }
}
