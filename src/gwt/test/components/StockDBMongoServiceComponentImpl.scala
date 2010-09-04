package gwt.test.components

import gwt.test.annotations.Logging
import gwt.test.annotations.MongoTransaction
import gwt.test.services.ComponentContext
import gwt.test.entities.mongo._
import scala.collection.mutable.ListBuffer
import collection.JavaConversions._

trait StockDBMongoServiceComponentImpl extends StockDBServiceComponent  {

  @MongoTransaction
  @Logging  
  class StockDBServiceImpl(val context: ComponentContext) extends StockDBService with ObjectConverter {
	  
	  def getCurrentStockQuote(symbol: String) : gwt.test.client.StockQuote = {
			val symCol = context.getSymCollection
			if(symbol == null) return new gwt.test.client.StockQuote
			val symbolQuery = Symbol where (Symbol.symbol is symbol) 
			val symbolResult = symbolQuery in symCol
			val sym = symbolResult.head
			val sqCol = context.getSQCollection
			val sqQuery = StockQuote where (StockQuote.symOID is sym.mongoOID.get) sortBy StockQuote.time.descending
			val sqResult = sqQuery in sqCol
			val gwtStockQuote = getGwtStockQuote(sqResult.head)
			gwtStockQuote.setSymbol(sym.symbol)
			gwtStockQuote.setName(sym.name)
			gwtStockQuote
	  }
   
	  def getStockHistory(symbol: String) : List[gwt.test.client.StockQuote] = {
			val symCol = context.getSymCollection
			if(symbol == null) return Nil
			val symbolQuery = Symbol where (Symbol.symbol is symbol) 
			val symbolResult = symbolQuery in symCol
			val sym = symbolResult.head
			val sqCol = context.getSQCollection
			val sqQuery = StockQuote where (StockQuote.symOID is sym.mongoOID.get) sortBy StockQuote.time.ascending
			val sqResult = sqQuery in sqCol
			val gwtStockQuotes = new  ListBuffer[gwt.test.client.StockQuote]
			sqResult.foreach(stockQuote => {
				val gwtStockQuote = getGwtStockQuote(stockQuote)
				gwtStockQuote.setSymbol(sym.symbol)
				gwtStockQuote.setName(sym.name)
				gwtStockQuotes += gwtStockQuote
			})
			gwtStockQuotes.toList
	  }
   
	  def getSymbols() : java.util.List[gwt.test.client.Symbol] = {
			val symCol = context.getSymCollection			
			val symbolQuery = Symbol sortBy Symbol.symbol.descending 
			val symbolResult = symbolQuery in symCol
			val gwtSymbols = new ListBuffer[gwt.test.client.Symbol]
			symbolResult.foreach(sym => {
				val gwtSymbol = getGwtSymbol(sym)
				gwtSymbols += gwtSymbol
			})
			gwtSymbols
	  }
	}
}