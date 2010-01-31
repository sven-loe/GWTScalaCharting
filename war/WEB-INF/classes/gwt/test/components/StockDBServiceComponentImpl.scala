package gwt.test.components

import gwt.test.annotations.Logging;
import gwt.test.annotations.Transaction;
import gwt.test.entities._
import gwt.test.services.ComponentContext

trait StockDBServiceComponentImpl extends StockDBServiceComponent with ObjectConverter {

  @Logging
  @Transaction 
  class StockDBServiceImpl(val context: ComponentContext) extends StockDBService {
	  def getCurrentStockQuote(symbol: String) : StockQuote = {
	    val em = context.getEntityManager()
	    val stockQuote = em.createQuery("select sq from StockQuote sq, Symbol s where sq.symbol.symbol = :symbol order by sq.time decending").setParameter("symbol", symbol).setMaxResults(1).getSingleResult.asInstanceOf[StockQuote];
	    stockQuote
	  }
   
	  def getStockHistory(symbol: String) : List[StockQuote] = {
	    val em = context.getEntityManager()
	    val stockQuotes = em.createQuery("select sq from StockQuote sq, Symbol s where sq.symbol.symbol = :symbol order by sq.time decending").setParameter("symbol", symbol).getResultList()
	    val stockQuotesArr = stockQuotes.toArray
	    val myStockQuotes = List.fromArray(stockQuotesArr.asInstanceOf[Array[StockQuote]])
	    myStockQuotes
	  }
   
	  def getSymbols() : List[Symbol] = {
	    val em = context.getEntityManager()
	    val symbols = em.createQuery("select s from Symbol s order by s.symbol decending").getResultList()
	    val symbolsArr = symbols.toArray
	    val mySymbols = List.fromArray(symbolsArr.asInstanceOf[Array[Symbol]])
	    mySymbols
	  }
  }
  
}
