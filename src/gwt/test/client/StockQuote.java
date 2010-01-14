package gwt.test.client;

import java.io.Serializable;
import java.util.Date;

public class StockQuote implements Serializable {

	private static final long serialVersionUID = 4742633284436858258L;

	private long id;
	private String currency;
	private Date time;
	private long last;
	private long dayHigh;
	private long dayLow;
	private long volume;
	private long adjLast;
	private String symbol;
	private String name;	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public long getLast() {
		return last;
	}

	public void setLast(long last) {
		this.last = last;
	}

	public long getDayHigh() {
		return dayHigh;
	}

	public void setDayHigh(long dayHigh) {
		this.dayHigh = dayHigh;
	}

	public long getDayLow() {
		return dayLow;
	}

	public void setDayLow(long dayLow) {
		this.dayLow = dayLow;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public long getAdjLast() {
		return adjLast;
	}

	public void setAdjLast(long adjLast) {
		this.adjLast = adjLast;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
