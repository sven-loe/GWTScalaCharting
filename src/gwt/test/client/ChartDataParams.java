package gwt.test.client;

public class ChartDataParams {
	private final String symbol;
	private final TimeFrame timeFrame;
	private final Integer xSize;
	private final Integer ySize;

	public ChartDataParams(String symbol, TimeFrame timeFrame, Integer xSize,
			Integer ySize) {
		super();
		if (symbol == null || timeFrame == null || xSize == null
				|| ySize == null) {
			throw new IllegalArgumentException("All values must not be null!"
					+ " symbol: " + symbol + " timeFrame: " + timeFrame
					+ " xSize: " + xSize + " ySize: " + ySize);
		}
		this.symbol = symbol;
		this.timeFrame = timeFrame;
		this.xSize = xSize;
		this.ySize = ySize;
	}

	public String getSymbol() {
		return symbol;
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	public Integer getxSize() {
		return xSize;
	}

	public Integer getySize() {
		return ySize;
	}

}
