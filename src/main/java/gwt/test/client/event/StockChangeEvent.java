package gwt.test.client.event;

import gwt.test.client.StockQuote;

import com.google.gwt.event.shared.GwtEvent;

public class StockChangeEvent extends GwtEvent<StockChangeEventHandler> {
	 public static Type<StockChangeEventHandler> TYPE = new Type<StockChangeEventHandler>();
	 private StockQuote stockQuote;
	 
	 public StockChangeEvent(StockQuote stockQuote) {
		 this.stockQuote = stockQuote;
	 }
	 
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<StockChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(StockChangeEventHandler handler) {
		handler.changeStock(this);
	}

}
