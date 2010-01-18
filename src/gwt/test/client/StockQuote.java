package gwt.test.client;

import java.io.Serializable;
import java.util.Date;

public class StockQuote implements Serializable {

	private static final long serialVersionUID = 4742633284436858258L;

	private long id;
	private String currency;
	private Date time;
	private String last;
	private String dayHigh;
	private String dayLow;
	private String volume;
	private String adjLast;
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

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getDayHigh() {
		return dayHigh;
	}

	public void setDayHigh(String dayHigh) {
		this.dayHigh = dayHigh;
	}

	public String getDayLow() {
		return dayLow;
	}

	public void setDayLow(String dayLow) {
		this.dayLow = dayLow;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getAdjLast() {
		return adjLast;
	}

	public void setAdjLast(String adjLast) {
		this.adjLast = adjLast;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
