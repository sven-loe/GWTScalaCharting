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
import gwt.test.annotations.Logging
import gwt.test.annotations.Transaction
import gwt.test.client.TimeFrame
import java.util.Calendar
import scala.collection.immutable.List
import scala.collection.mutable.ListBuffer
 
trait StockDBServiceComponent {
	val stockDBService: StockDBService
 	
	trait StockDBService {	  
	  def getCurrentStockQuote(symbol: String) : gwt.test.client.StockQuote
   
	  def getStockHistory(symbol: String) : List[gwt.test.client.StockQuote]
   
	  def getSymbols() : List[gwt.test.client.Symbol]
	   
	  def filterStockHistory(chartDataParams : gwt.test.client.ChartDataParams, history : List[gwt.test.client.StockQuote]) : List[gwt.test.client.StockQuote] = {	 	  	 	  
	 	  var filteredHistory = List[gwt.test.client.StockQuote]()
	 	  chartDataParams.getTimeFrame match {
	 	 	  case TimeFrame.All => {
	 	 	 	  filteredHistory = history
	 	 	  }
	 	 	  case TimeFrame.Years5 => {
	 	 		  val cal = Calendar.getInstance
	 	 	 	  cal.add(Calendar.YEAR, -5)
	 	 	 	  filteredHistory = history.filter(_.getTime.after(cal.getTime))	 	 	  
	 	 	  }
	 	 	  case TimeFrame.Years2 => {
	 	 		  val cal = Calendar.getInstance
	 	 		  cal.add(Calendar.YEAR, -2)
	 	 		  filteredHistory = history.filter(_.getTime.after(cal.getTime))
	 	 	  }
	 	 	  case TimeFrame.Year1 => {
	 	 	 	  val cal = Calendar.getInstance
	 	 	 	  cal.add(Calendar.YEAR, -2)
	 	 	 	  filteredHistory = history.filter(_.getTime.after(cal.getTime))
	 	 	  }
	 	 	  case TimeFrame.Months6 => {
	 	 	 	  val cal = Calendar.getInstance
	 	 	 	  cal.add(Calendar.MONTH, -6)
	 	 	 	  filteredHistory = history.filter(_.getTime.after(cal.getTime))
	 	 	  }
	 	 	   case TimeFrame.Months2 => {
	 	 	 	  val cal = Calendar.getInstance
	 	 	 	  cal.add(Calendar.MONTH, -2)
	 	 	 	  filteredHistory = history.filter(_.getTime.after(cal.getTime))
	 	 	  }
	 	 	    case TimeFrame.Month1 => {
	 	 	 	  val cal = Calendar.getInstance
	 	 	 	  cal.add(Calendar.MONTH, -1)
	 	 	 	  filteredHistory = history.filter(_.getTime.after(cal.getTime))
	 	 	  } 
	 	 	 case TimeFrame.Days14 => {
	 	 	 	  val cal = Calendar.getInstance
	 	 	 	  cal.add(Calendar.DAY_OF_YEAR, -14)
	 	 	 	  filteredHistory = history.filter(_.getTime.after(cal.getTime))
	 	 	  }
	 	  }	 	  
	 	   
	 	  if(filteredHistory.length > chartDataParams.getxSize.intValue) {
	 	 	val factor = filteredHistory.length.doubleValue / chartDataParams.getxSize.intValue.doubleValue
	 	 	var counter = 0.0
	 	 	val myFilteredHistory = ListBuffer[gwt.test.client.StockQuote]()
	 	 	for(i <- 0 until chartDataParams.getxSize.intValue) {
	 	 		myFilteredHistory += filteredHistory(Math.round(counter).intValue)
	 	 		counter += factor	 	 		
	 	 	}
	 	 	filteredHistory = myFilteredHistory.toList
	 	  }
	 	   
	 	  filteredHistory
	  }
	}
}
