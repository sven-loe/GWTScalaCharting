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

import gwt.test.annotations.Logging;
import gwt.test.annotations.Transaction;
import gwt.test.entities.jpa._
import gwt.test.services.ComponentContext
import java.util.Calendar
import scala.collection.mutable._ 
import gwt.test.persistence._
 

trait StockDBServiceComponentImpl extends StockDBServiceComponent with ObjectConverter {

  @Logging
  @Transaction
  class StockDBServiceImpl(val context: ComponentContext) extends StockDBService {
	  
      def getCurrentStockQuote(symbol: String) : gwt.test.client.StockQuote = {
	    try{
	      val em = context.getEntityManager()
	      val sQuery = new SQuery(em.createQuery("select sq from StockQuote sq, Symbol s where sq.symbol.symbol = :symbol order by sq.time desc").setParameter("symbol", symbol).setMaxResults(1))
	      val stockQuote = sQuery.getSingleResult[StockQuote]
	      getGwtStockQuote(stockQuote)
	    } catch {
	      case ex: Exception => {
	        val stockQuote = new gwt.test.client.StockQuote
	        stockQuote.setCurrency("USD")
	        val cal = Calendar.getInstance
	        stockQuote.setTime(cal.getTime)
	        stockQuote.setLast("0")
	        stockQuote.setDayHigh("0")
	        stockQuote.setDayLow("0")
	        stockQuote.setVolume("0")
	        stockQuote.setAdjLast("0")
	        stockQuote.setSymbol("X")
	        stockQuote.setName("XX")
	        stockQuote
	      }
	    }
	  }
   
	  def getStockHistory(symbol: String) : List[gwt.test.client.StockQuote] = {
	    val em = context.getEntityManager()	 
	    val sQuery = new SQuery(em.createQuery("select count(sq) from StockQuote sq where sq.symbol.symbol = :symbol").setParameter("symbol", symbol))
	    val stockQuoteCount = sQuery.getSingleResult[Long]	
	    println("Availiable quotes: "+stockQuoteCount)
	    var filter = 1L
        if(stockQuoteCount > 1000) filter = (Math.floor(stockQuoteCount / 1000)).toLong 
        val sQuery1 = new SQuery(em.createQuery("select sq from StockQuote sq, Symbol s where sq.symbol.symbol = :symbol and mod(sq.dayOfYear,:filter) = 0 order by sq.time").setParameter("symbol", symbol).setParameter("filter", filter))        
        val stockQuotes = sQuery1.getResultList[StockQuote]	     
        println("StockQuotes of history: "+stockQuotes.size)
        if(stockQuotes.size > 0) {	    	
        	val jpaStockQuotes = stockQuotes.toList
        	jpaStockQuotes.map(jpaQuote => getGwtStockQuote(jpaQuote))        	
	    } else  {
	    	Nil
	    }
	  }
   
	  def getSymbols() : List[gwt.test.client.Symbol] = {
	    val em = context.getEntityManager()
	    val sQuery = new SQuery(em.createQuery("select s from Symbol s order by s.symbol desc"))
	    val symbols = sQuery.getResultList[Symbol]
        val mySymbols = new ListBuffer[gwt.test.client.Symbol]
        symbols.foreach(symbol => {
        	val mySymbol = getGwtSymbol(symbol)
            mySymbols += mySymbol
        })        
    	mySymbols.toList
	  }
  }
  
}
