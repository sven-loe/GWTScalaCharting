package gwt.test.client.component;

import gwt.test.client.TimeFrame;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class StockQuotePlace extends Place {
	private String symbol;
	private TimeFrame timeFrame;

	public StockQuotePlace(String token) {
		if (token != null) {
			String[] params = token.split("_");
			this.symbol = params[0];
			this.timeFrame = TimeFrame.valueOf(params[1]);
		}
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(TimeFrame timeFrame) {
		this.timeFrame = timeFrame;
	}

	public static class Tokenizer implements PlaceTokenizer<StockQuotePlace> {
		@Override
		public String getToken(StockQuotePlace place) {
			String token = place.getSymbol() + "_" + place.getTimeFrame();
			return token;
		}

		@Override
		public StockQuotePlace getPlace(String token) {
			return new StockQuotePlace(token);
		}
	}

}
