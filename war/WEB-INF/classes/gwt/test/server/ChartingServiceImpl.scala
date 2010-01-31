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
