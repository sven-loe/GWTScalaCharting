package gwt.test.server

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import gwt.test.client.ChartingService;
import gwt.test.client.TimeFrame;
import gwt.test.client.StockQuote;
import gwt.test.servlet.JFreeChartServlet;
import gwt.test.services.DbClass;
import java.util.Date;


class ChartingServiceImpl extends RemoteServiceServlet with ChartingService {

  val dbClass = new DbClass
   
  def getChart(symbol: String, timeFrame: TimeFrame) : String = {    
    return "<img style=\"border: 0;\" src=\"./gwtcharting/jFreeChart?" + JFreeChartServlet.PARAMETER_SYMBOL + "=" + symbol + "&" + JFreeChartServlet.PARAMETER_TIMEFRAME + "=" + timeFrame.getTimeFrame() + "\" " + "/>";
  }
  
  def getLastStockQuote(symbol: String) : StockQuote = {
    val sq = new StockQuote
    sq.setCurrency("USD")
    sq.setTime(new Date)
    sq.setLast("10.5")
    sq.setDayHigh("11.5")
    sq.setDayLow("10.0")
    sq.setVolume("100000")
    sq.setAdjLast("10.4")
    sq.setSymbol("MySymbol")
    sq.setName("MyName")
    return sq
  }
  
}
