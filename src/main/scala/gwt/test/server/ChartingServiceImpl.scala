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

package gwt.test.server

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import gwt.test.client.ChartingService;
import gwt.test.client.TimeFrame;
import gwt.test.client.StockQuote;
import gwt.test.servlet.JFreeChartServlet;
import gwt.test.services.DbObject;
import java.util.Date;
import java.util.ArrayList
import gwt.test.client.ChartDataParams
import gwt.test.components.ObjectConversion._

class ChartingServiceImpl extends RemoteServiceServlet with ChartingService {  
    
  override def getChart(symbol: String, timeFrame: TimeFrame) : String = {    
    return "<img style=\"border: 0;\" src=\"./gwtcharting/jFreeChart?" + JFreeChartServlet.PARAMETER_SYMBOL + "=" + symbol + "&" + JFreeChartServlet.PARAMETER_TIMEFRAME + "=" + timeFrame.toString + "\" " + "/>";
  }
  
  override def getChartData(chartDataParams: gwt.test.client.ChartDataParams) : gwt.test.client.ChartData = {
	 val stockQuotes = DbObject.factory.stockDBService.getStockHistory(chartDataParams.getSymbol)
	 val filteredStockQuotes = DbObject.factory.stockDBService.filterStockHistory(chartDataParams, stockQuotes)
	 val javaStockQuotes : java.util.List[gwt.test.client.ChartPoint] = filteredStockQuotes
	 val stockQuote = stockQuotes.first
	 val chartData = new gwt.test.client.ChartData(javaStockQuotes, stockQuote.getName, stockQuote.getName, "time", "value")
	 chartData
  }
  
  override def getLastStockQuote(symbol: String) : StockQuote = {
    val jpaSq = DbObject.factory.stockDBService.getCurrentStockQuote(symbol)
    jpaSq    
  }
   
  override def importStockQuotes(symbol: String) : java.lang.Long = {
    val stockQuotes = DbObject.factory.stockImporter.importStockHistory(symbol)
    val stockQuoteNumber = DbObject.factory.stockImporter.storeStockHistory(stockQuotes)
    stockQuoteNumber 
  }
  
  override def getSymbols() : java.util.List[gwt.test.client.Symbol] = {
    val symbols = DbObject.factory.stockDBService.getSymbols
    //GWT requires a real java list
    val javaSymbols : java.util.List[gwt.test.client.Symbol] = symbols     
    println("Number of symbols: "+ symbols.size)
    javaSymbols
  }
}
