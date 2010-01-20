package gwt.test.components

import gwt.test.entities._

trait StockImporterComponent {

  val stockImporter: StockImporter
  
  trait StockImporter {
    def importStockHistory(symbol: String) : List[StockQuote]
 
	def storeStockHistory(quotes: List[StockQuote]) : Long
 
	def updateStockHistory(quotes: List[StockQuote]) : Long
  }
   
}
