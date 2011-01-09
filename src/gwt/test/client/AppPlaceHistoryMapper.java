package gwt.test.client;

import gwt.test.client.component.StockQuotePlace;
import gwt.test.client.component.StockQuotePlace.Tokenizer;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers(StockQuotePlace.Tokenizer.class)
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
