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


class ChartingServiceImpl extends RemoteServiceServlet with ChartingService {  
   
  def getChart(symbol: String, timeFrame: TimeFrame) : String = {    
    return "<img style=\"border: 0;\" src=\"./gwtcharting/jFreeChart?" + JFreeChartServlet.PARAMETER_SYMBOL + "=" + symbol + "&" + JFreeChartServlet.PARAMETER_TIMEFRAME + "=" + timeFrame.getTimeFrame() + "\" " + "/>";
  }
  
  def getLastStockQuote(symbol: String) : StockQuote = {
    val jpaSq = DbObject.stockDBService.getCurrentStockQuote(symbol)
    jpaSq    
  }
  
}
