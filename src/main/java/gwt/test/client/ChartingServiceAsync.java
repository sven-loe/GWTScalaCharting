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

package gwt.test.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChartingServiceAsync {
	void getChart(String symbol, TimeFrame timeFrame, AsyncCallback<String> callBack);
	void getChartData(ChartDataParams charDataParams, AsyncCallback<ChartData> callBack);
	void getLastStockQuote(String symbol, AsyncCallback<StockQuote> callBack);
	void importStockQuotes(String symbol, AsyncCallback<Long> callBack);
	void getSymbols(AsyncCallback<List<Symbol>> callBack);	
}
