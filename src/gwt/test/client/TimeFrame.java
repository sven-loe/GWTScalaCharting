package gwt.test.client;

public enum TimeFrame {
	All("All"),
	Years5("5Years"),
	Years2("2Years"),
	Year1("1Year"),
	Months6("6Months"),
	Months2("2Months"),
	Month1("1Month");
	
	private final String timeFrame;
	
	TimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}
	
	public String getTimeFrame() {
		return timeFrame;
	}
}
