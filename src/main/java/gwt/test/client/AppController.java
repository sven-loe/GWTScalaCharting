package gwt.test.client;

import gwt.test.client.event.StockChangeEvent;
import gwt.test.client.event.StockChangeEventHandler;
import gwt.test.client.presenter.StockQuotePresenter;
import gwt.test.client.view.StockQuoteView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final ChartingServiceAsync rpcService;
	private HasWidgets container;

	public AppController(ChartingServiceAsync rpcService, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);
		eventBus.addHandler(StockChangeEvent.TYPE, new StockChangeEventHandler() {
			@Override
			public void changeStock(StockChangeEvent stockChangeEvent) {
				// update other presenters
				System.out.println("EvenBus fired StockChangeEvent.");
			}
		});
	}

	public void go(final HasWidgets container) {
		this.container = container;
	    
	    if ("".equals(History.getToken())) {
	      History.newItem("chart");
	    }
	    else {
	      History.fireCurrentHistoryState();
	    }
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = new StockQuotePresenter(rpcService, eventBus, new StockQuoteView(eventBus));
			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

}
