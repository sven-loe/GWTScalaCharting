package gwt.test.client;

import gwt.test.client.component.StockQuoteActivity;
import gwt.test.client.component.StockQuotePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {
	private ClientFactory clientFactory;
	
	public AppActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if(place instanceof StockQuotePlace) {
			return new StockQuoteActivity((StockQuotePlace) place, clientFactory);
		}
		return null;
	}

}
