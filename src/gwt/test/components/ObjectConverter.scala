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
