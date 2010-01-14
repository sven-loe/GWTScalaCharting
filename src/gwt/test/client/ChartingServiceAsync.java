package gwt.test.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChartingServiceAsync {
	void getChart(String symbol, TimeFrame timeFrame, AsyncCallback<String> callBack);
}
