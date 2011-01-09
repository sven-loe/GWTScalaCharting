package gwt.test.client.component;

import gwt.test.client.ChartData;
import gwt.test.client.ChartDataParams;
import gwt.test.client.StockQuote;
import gwt.test.client.Symbol;
import gwt.test.client.TimeFrame;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface StockQuoteView extends IsWidget {
	
	public static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network " + "connection and try again.";
	
	public final String SHOW_BUTTON_ID = "sqChartShowButton";
	public final String IMPORT_BUTTON_ID = "sqChartImportButton";
	void setName(String name);
	void setSymbol(String symbol);
	void setTimeFrame(int itemNo);
	void setStockQuote(StockQuote stockQuote);
	void setSymbols(List<Symbol> symbols);
	void setChartLink(String link);
	void setChartData(ChartData chartData);
	void setPresenter(Presenter presenter);	
	String getSymbol();
	TimeFrame getTimeFrame();
	void showDialogBox(String headerText, String style, String content);
	ChartDataParams getChartDataParams();
	
	public interface Presenter {
        void goTo(Place place);
    }
}
