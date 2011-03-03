package gwt.test.components

import gwt.test.annotations._
import gwt.test.services.ComponentContext
import gwt.test.entities.mongo._

trait StockImporterMongoComponentImpl extends StockImporterComponent {

	@MongoTransaction
	@Logging  
	class StockImporterImpl(val context: ComponentContext) extends StockImporter with ObjectConverter {
		
		override def storeStockHistory(quotes: List[gwt.test.entities.jpa.StockQuote]) : Long = {
			updateStockHistory(quotes)
		}
		
		override def updateStockHistory(quotes: List[gwt.test.entities.jpa.StockQuote]) : Long = {
			var number = 0L
			val symCol = context.getSymCollection
			if(quotes == null || quotes.head == null || quotes.head.symbol == null) return number
			val symbolQuery = Symbol where (Symbol.symbol is quotes.head.symbol.symbol)
			val symbolResult = symbolQuery in symCol
			val sym = symbolResult.head
			val sqCol = context.getSQCollection
			val sqQuery = StockQuote where (StockQuote.symOID is sym.mongoOID.get)
			val sqResult = sqQuery in sqCol
			sqResult.foreach(mongoSQ => {
				sqCol -= mongoSQ
			})
			quotes.foreach(jpaSQ => {
				val stockQuote = getMongoStockQuote(jpaSQ)
				stockQuote.symOID = sym.mongoOID.get
				sqCol += stockQuote
				number += 1
			})
			number 
		}
		
	}
}