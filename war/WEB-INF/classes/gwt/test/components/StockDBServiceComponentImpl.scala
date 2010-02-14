package gwt.test.components

import gwt.test.annotations.Logging;
import gwt.test.annotations.Transaction;
import gwt.test.entities._
import gwt.test.services.ComponentContext
import java.util.Calendar
import scala.collection.mutable  

trait StockDBServiceComponentImpl extends StockDBServiceComponent with ObjectConverter {

  @Logging
  @Transaction 
  class StockDBServiceImpl(val context: ComponentContext) extends StockDBService {
	  def getCurrentStockQuote(symbol: String) : gwt.test.client.StockQuote = {
	    try{
	      val em = context.getEntityManager()
	      val stockQuote = em.createQuery("select sq from StockQuote sq, Symbol s where sq.symbol.symbol = :symbol order by sq.time").setParameter("symbol", symbol).setMaxResults(1).getSingleResult.asInstanceOf[StockQuote];
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
   
	  def getStockHistory(symbol: String) : List[StockQuote] = {
	    val em = context.getEntityManager()
	    val stockQuotes = em.createQuery("select sq from StockQuote sq, Symbol s where sq.symbol.symbol = :symbol order by sq.time").setParameter("symbol", symbol).getResultList()
	    if(stockQuotes.size() > 0) {
	    	val stockQuotesArr = stockQuotes.toArray
	    	val myStockQuotes = List.fromArray(stockQuotesArr.asInstanceOf[Array[StockQuote]])	    
	    	myStockQuotes
	    } else  {
	    	val myStockQuotes = Nil
	    	myStockQuotes
	    }
	  }
   
	  def getSymbols() : List[Symbol] = {
	    val em = context.getEntityManager()
	    val symbols = em.createQuery("select s from Symbol s order by s.symbol").getResultList()
	    if(symbols.size() > 0) {
	    	val symbolsArr = symbols.toArray
	    	val mySymbols = List.fromArray(symbolsArr.asInstanceOf[Array[Symbol]])
	    	mySymbols
	    } else {
	    	val mySymbols = Nil
	    	mySymbols
	    }
	  }
  }
  
}
