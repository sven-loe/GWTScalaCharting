package gwt.test.components

import gwt.test.entities._

trait StockDBServiceComponent {
	val stockDBService: StockDBService
 
	trait StockDBService {	  
	  def getCurrentStockQuote(symbol: String) : StockQuote
   
	  def getStockHistory(symbol: String) : List[StockQuote]
   
	  def getSymbols() : List[Symbol]
	}
}
