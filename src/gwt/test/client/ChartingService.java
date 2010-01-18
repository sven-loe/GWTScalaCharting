package gwt.test.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("chart")
public interface ChartingService extends RemoteService {
	String getChart(String symbol, TimeFrame timeFrame);
	StockQuote getLastStockQuote(String symbol);
}
