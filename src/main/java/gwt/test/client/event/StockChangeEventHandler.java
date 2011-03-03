package gwt.test.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface StockChangeEventHandler extends EventHandler {
	void changeStock(StockChangeEvent stockChangeEvent);
}
