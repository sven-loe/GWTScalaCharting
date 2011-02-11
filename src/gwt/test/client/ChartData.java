package gwt.test.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChartData implements Serializable {

	private static final long serialVersionUID = 6601923301023861473L;
	
	private List<ChartPoint> points = new ArrayList<ChartPoint>(); 
	private String title;
	private String legendLabel;
	private String xAxisTitle;
	private String yAxisTitle;
	
	public ChartData() {
		
	}
	
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
	
	public String getTitle() {
		return title;
	}
	
	public String getLegendLabel() {
		return legendLabel;
	}
	
	public String getxAxisTitle() {
		return xAxisTitle;
	}
	
	public String getyAxisTitle() {
		return yAxisTitle;
	}
	
}
