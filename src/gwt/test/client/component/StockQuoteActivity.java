package gwt.test.client.component;

import gwt.test.client.ChartData;
import gwt.test.client.ChartingService;
import gwt.test.client.ChartingServiceAsync;
import gwt.test.client.ClientFactory;
import gwt.test.client.StockQuote;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;

public class StockQuoteActivity extends AbstractActivity implements StockQuoteView.Presenter {

	private final ChartingServiceAsync chartingService = GWT.create(ChartingService.class);
	private final ClientFactory clientFactory;
	private StockQuotePlace place;
	private final StockQuoteView sqView;

	public StockQuoteActivity(StockQuotePlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.place = place;
		this.sqView = clientFactory.getStockQuoteView();
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {		
		sqView.setPresenter(this);
		containerWidget.setWidget(sqView.asWidget());
		eventBus.addHandler(new GwtEvent.Type<ClickHandler>(), new MyClickHandler());
	}

	class MyClickHandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			Widget sender = (Widget) event.getSource();	
			sender.getElement().getId();
			if (StockQuoteView.SHOW_BUTTON_ID.equals(sender.getElement().getId())) {
				sendNameToServer();
			} else if (StockQuoteView.IMPORT_BUTTON_ID.equals(sender.getElement().getId())) {
				importStockQuotes();
			}
		}
		
		private void importStockQuotes() {
			chartingService.importStockQuotes(sqView.getSymbol(), new AsyncCallback<Long>() {
				public void onFailure(Throwable caught) {
					// Show the RPC error message to the user
					sqView.showDialogBox("Remote Procedure Call - Failure", "serverResponseLabelError", StockQuoteView.SERVER_ERROR);
				}

				public void onSuccess(Long result) {
					sqView.showDialogBox("Successfuly imported StockQuotes.", "serverResponseLabelSuccess", "Successfuly imported " + result + "StockQuotes");
				}

			});
		}

		private void sendNameToServer() {
			chartingService.getLastStockQuote(sqView.getSymbol(), new AsyncCallback<StockQuote>() {
				public void onFailure(Throwable caught) {
					sqView.showDialogBox("Remote Procedure Call - Failure", "serverResponseLabelError", StockQuoteView.SERVER_ERROR);
				}

				public void onSuccess(StockQuote result) {
					sqView.setStockQuote(result);
				}

			});
			chartingService.getChartData(sqView.getChartDataParams(), new AsyncCallback<ChartData>() {
				@Override
				public void onFailure(Throwable caught) {
					sqView.showDialogBox("Remote Procedure Call - Failure", "serverResponseLabelError", StockQuoteView.SERVER_ERROR);
				}
				@Override
				public void onSuccess(ChartData result) {
					sqView.setChartData(result);
				}
			});
//			chartingService.getChart(sqView.getSymbol(), sqView.getTimeFrame(), new AsyncCallback<String>() {
//				public void onFailure(Throwable caught) {
//					sqView.showDialogBox("Remote Procedure Call - Failure", "serverResponseLabelError", StockQuoteView.SERVER_ERROR);
//				}
//
//				public void onSuccess(String result) {
//					sqView.setChartLink(result);
//				}
//			});
		}
	}
	
	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

}
