package gwt.test.client;

import gwt.test.client.component.StockQuoteView;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

public interface ClientFactory {
    EventBus getEventBus();
    PlaceController getPlaceController();
    StockQuoteView getStockQuoteView();
}

