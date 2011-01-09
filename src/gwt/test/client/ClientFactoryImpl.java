package gwt.test.client;

import gwt.test.client.component.StockQuoteView;
import gwt.test.client.component.StockQuoteViewImpl;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactoryImpl implements ClientFactory {

	private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);
    private final StockQuoteView stockQuoteView = new StockQuoteViewImpl(eventBus);
	
	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public StockQuoteView getStockQuoteView() {
		return stockQuoteView;
	}

}
