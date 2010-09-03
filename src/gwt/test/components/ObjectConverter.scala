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

import gwt.test.entities.jpa._
import java.util.Calendar
import java.util.Date
 
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
      gwtStockQuote.setTime(new Date(stockQuote.time.getTime))
      gwtStockQuote.setVolume(stockQuote.volume.toString)
	  gwtStockQuote
	}	
  
	def getGwtStockQuote(stockQuote: gwt.test.entities.mongo.StockQuote) : gwt.test.client.StockQuote = {
	  val gwtStockQuote = new gwt.test.client.StockQuote
	  gwtStockQuote.setAdjLast((stockQuote.adjLast / 100).toString)
	  gwtStockQuote.setCurrency(stockQuote.currency)
	  gwtStockQuote.setDayHigh((stockQuote.dayHigh / 100).toString)
	  gwtStockQuote.setDayLow((stockQuote.dayLow / 100).toString)	  
	  gwtStockQuote.setLast((stockQuote.last / 100).toString)
//	  gwtStockQuote.setName(stockQuote.symbol.name)
//      gwtStockQuote.setSymbol(stockQuote.symbol.symbol)
      gwtStockQuote.setTime(new Date(stockQuote.time.getTime))
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
 
	def getGwtSymbol(symbol: Symbol) : gwt.test.client.Symbol = {
	  val gwtSymbol = new gwt.test.client.Symbol 
	  gwtSymbol.setId(symbol.id)
	  gwtSymbol.setSymbol(symbol.symbol)
	  gwtSymbol.setName(symbol.name)
	  gwtSymbol.setValue(symbol.value)
	  gwtSymbol 
	}
	
	def getGwtSymbol(symbol: gwt.test.entities.mongo.Symbol) : gwt.test.client.Symbol = {
	  val gwtSymbol = new gwt.test.client.Symbol 	  
	  gwtSymbol.setSymbol(symbol.symbol)
	  gwtSymbol.setName(symbol.name)
	  gwtSymbol.setValue(symbol.value)
	  gwtSymbol 
	}
	
	def getMongoStockQuote(jpaStockQuote: StockQuote) : gwt.test.entities.mongo.StockQuote = {
		val mongoSQ = new gwt.test.entities.mongo.StockQuote
		mongoSQ.time = jpaStockQuote.time
		mongoSQ.currency = jpaStockQuote.currency
		mongoSQ.last = jpaStockQuote.last
		mongoSQ.dayHigh = jpaStockQuote.dayHigh
		mongoSQ.dayLow = jpaStockQuote.dayLow
		mongoSQ.volume = jpaStockQuote.volume
		mongoSQ.adjLast = jpaStockQuote.adjLast
		mongoSQ.dayOfYear = jpaStockQuote.dayOfYear
		mongoSQ
	}
	
	def getMongoSymbol(jpaSymbol: Symbol) :gwt.test.entities.mongo.Symbol = {
		val mongoSym = new gwt.test.entities.mongo.Symbol
		mongoSym.symbol = jpaSymbol.symbol
		mongoSym.name = jpaSymbol.name
		mongoSym.value = jpaSymbol.value
		mongoSym
	}
}
