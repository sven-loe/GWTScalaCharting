package gwt.test.client;

import java.util.ArrayList;
import java.util.List;

public class ChartData {
	private List<ChartPoint> points = new ArrayList<ChartPoint>(); 
	private String title;
	private String legendLabel;
	private String xAxisTitle;
	private String yAxisTitle;
	public ChartData(List<ChartPoint> points, String title, String legendLabel,
			String xAxisTitle, String yAxisTitle) {
		super();
		if(points == null) {
			throw new IllegalArgumentException("List<ChartPoint must not be null!");
		}
		this.points = points;
		this.title = title;
		this.legendLabel = legendLabel;
		this.xAxisTitle = xAxisTitle;
		this.yAxisTitle = yAxisTitle;
	}
	public List<ChartPoint> getPoints() {
		return points;
	}
	public void setPoints(List<ChartPoint> points) {
		this.points = points;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLegendLabel() {
		return legendLabel;
	}
	public void setLegendLabel(String legendLabel) {
		this.legendLabel = legendLabel;
	}
	public String getxAxisTitle() {
		return xAxisTitle;
	}
	public void setxAxisTitle(String xAxisTitle) {
		this.xAxisTitle = xAxisTitle;
	}
	public String getyAxisTitle() {
		return yAxisTitle;
	}
	public void setyAxisTitle(String yAxisTitle) {
		this.yAxisTitle = yAxisTitle;
	}
}
