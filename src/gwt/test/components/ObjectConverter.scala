package gwt.test.components

import gwt.test.entities._
import java.util.Calendar

trait ObjectConverter {
	def getGwtStockQuote(stockQuote: StockQuote) : gwt.test.client.StockQuote = {
	  val gwtStockQuote = new gwt.test.client.StockQuote
	  gwtStockQuote.setAdjLast((stockQuote.adjLast / 100).toString)
	  gwtStockQuote.setCurrency(stockQuote.currency)
	  gwtStockQuote.setDayHigh((stockQuote.dayHigh / 100).toString)
	  gwtStockQuote.setDayLow((stockQuote.dayLow / 100).toString)
	  gwtStockQuote.setId(stockQuote.id)
	  gwtStockQuote.setLast((stockQuote.last / 100).toString)
	  gwtStockQuote.setName(stockQuote.symbol.name)
      gwtStockQuote.setSymbol(stockQuote.symbol.symbol)
      gwtStockQuote.setTime(stockQuote.time)
      gwtStockQuote.setVolume(stockQuote.volume.toString)
	  gwtStockQuote
	}	
 
	def getEntityStockQuote(gwtStockQuote: gwt.test.client.StockQuote) : StockQuote = {
		val stockQuote = new StockQuote
		stockQuote.symbol = new Symbol
		stockQuote.adjLast = java.lang.Long.parseLong(gwtStockQuote.getAdjLast)
		stockQuote.currency = gwtStockQuote.getCurrency
		stockQuote.dayHigh = java.lang.Long.parseLong(gwtStockQuote.getDayHigh)
		stockQuote.id = gwtStockQuote.getId
		stockQuote.last = java.lang.Long.parseLong(gwtStockQuote.getLast)
		stockQuote.symbol.name = gwtStockQuote.getName
		stockQuote.symbol.symbol = gwtStockQuote.getSymbol
		stockQuote.time = gwtStockQuote.getTime
		stockQuote.volume = java.lang.Long.parseLong(gwtStockQuote.getVolume)
		stockQuote
	}
}
