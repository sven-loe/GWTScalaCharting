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

package gwt.test.servlet

import javax.servlet.http.HttpServlet;
import org.jfree.data.time.TimeSeries;
import java.util.Calendar;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.entity.StandardEntityCollection;
import java.io.BufferedOutputStream;
import org.jfree.chart.ChartUtilities;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import gwt.test.services.DbObject;

class JFreeChartServlet extends HttpServlet {  		
  
	override def doGet(req: HttpServletRequest, resp: HttpServletResponse) = {
		val symbol = req.getParameter(JFreeChartServlet.PARAMETER_SYMBOL)
		val timeframe = req.getParameter(JFreeChartServlet.PARAMETER_TIMEFRAME)
		val myDay = new MyDay(1,1,2000)
		val timeSeries = new TimeSeries(symbol, myDay.getClass)
		val cal = Calendar.getInstance()
  
//		for(i <- 1 to 4) {				  
//		  var day = new MyDay(cal.get(Calendar.DAY_OF_MONTH) - i,cal.get(Calendar.MONTH) +1, cal.get(Calendar.YEAR))
//		  if("RDY" == symbol) {
//		    timeSeries.addOrUpdate(day, 5 - i);
//		  } else { 
//		    timeSeries.addOrUpdate(day, i);
//          }		    
//		} 
		var start = System.currentTimeMillis
		val stockQuotes = DbObject.factory.stockDBService.getStockHistory(symbol)
		println("Get history from DB: "+(System.currentTimeMillis-start)+" ms");
		stockQuotes.foreach(sq => {
			cal.setTime(sq.time) 
			var day = new MyDay(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) +1, cal.get(Calendar.YEAR))
			timeSeries.addOrUpdate(day, sq.adjLast)
		})
		start = System.currentTimeMillis
		val xyDataset = new TimeSeriesCollection(timeSeries)
		val chart = ChartFactory.createTimeSeriesChart("Chart for: " + symbol, "Time", "Price", xyDataset,
				true, true, false)
		val info = new ChartRenderingInfo(new StandardEntityCollection())
		val os = new BufferedOutputStream(resp.getOutputStream())
		ChartUtilities.writeChartAsPNG(os, chart, 1000, 700, info)
		println("Render chart: "+(System.currentTimeMillis-start)+" ms")
	}
  
}

object JFreeChartServlet {
  val PARAMETER_SYMBOL: String = "symbol"
  val PARAMETER_TIMEFRAME: String = "timeframe";
}

class MyDay(val day: Int, val month: Int, val year: Int) extends Day(day, month, year) {
  
}