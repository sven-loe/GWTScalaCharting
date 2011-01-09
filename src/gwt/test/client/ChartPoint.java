package gwt.test.client;

import java.util.Date;

public class ChartPoint {
	private final double value;
	private final Date time;

	public ChartPoint(double value, Date time) {
		this.value = value;
		if (time == null) {
			throw new IllegalArgumentException("Time must not be null!");
		}
		this.time = time;
	}

	public double getValue() {
		return value;
	}

	public Date getTime() {
		return time;
	}
}
