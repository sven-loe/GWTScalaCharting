package gwt.test.client.component;

import gwt.test.client.ChartData;
import gwt.test.client.ChartDataParams;
import gwt.test.client.ChartPoint;
import gwt.test.client.ChartingService;
import gwt.test.client.ChartingServiceAsync;
import gwt.test.client.StockQuote;
import gwt.test.client.Symbol;
import gwt.test.client.TimeFrame;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gchart.client.GChart;
import com.googlecode.gchart.client.GChart.SymbolType;

public class StockQuoteViewImpl extends Composite implements StockQuoteView {
	
	private final List<Symbol> symbols = new ArrayList<Symbol>();
	private final SuggestBox symbol;
	private final SuggestBox name;
	private final ListBox timeframe;	
	private final MultiWordSuggestOracle nameOracle = new MultiWordSuggestOracle();
	private final MultiWordSuggestOracle symbolOracle = new MultiWordSuggestOracle();
	private Widget contents;	
	private ChartData chartData;	
	private int xChartSize = 1000;
	private int yChartSize = 700;
	private final StockQuoteDisplay stockQuoteDisplay;	
	private Presenter presenter;
	private final DockPanel dock = new DockPanel();
	
	
	public StockQuoteViewImpl(final EventBus eventBus) {		
		super.initWidget(dock);
		
		final Label nameLabel = new Label("Name:");
		final Label symbolLabel = new Label("Symbol:");
		final Label timeFrameLabel = new Label("Timeframe:");				
		this.symbol = new SuggestBox(symbolOracle);
		this.name = new SuggestBox(nameOracle);		
		
		this.timeframe = new ListBox();
		timeframe.addItem("All", "All");
		timeframe.addItem("5 Years", "5Y");
		timeframe.addItem("2 Years", "2Y");
		timeframe.addItem("1 Year", "1Y");
		timeframe.addItem("6 Months", "6M");
		timeframe.addItem("1 Month", "1M");
		timeframe.addItem("14 Days", "14D");
		
		final Button showButton = new Button("Show");
		showButton.getElement().setId(StockQuoteView.SHOW_BUTTON_ID);
		final Button importButton = new Button("Import");
		importButton.getElement().setId(StockQuoteView.IMPORT_BUTTON_ID);

		final HorizontalPanel hPanel1 = new HorizontalPanel();
		hPanel1.setSpacing(5);
		hPanel1.add(nameLabel);
		hPanel1.add(name);
		hPanel1.add(symbolLabel);
		hPanel1.add(symbol);

		final HorizontalPanel hPanel2 = new HorizontalPanel();
		hPanel2.setSpacing(5);
		hPanel2.add(timeFrameLabel);
		hPanel2.add(timeframe);
		hPanel2.add(showButton);
		hPanel2.add(importButton);

		final VerticalPanel vPanel1 = new VerticalPanel();
		vPanel1.add(hPanel1);
		vPanel1.add(hPanel2);				

		this.stockQuoteDisplay = new StockQuoteDisplay();
		
		final HorizontalPanel hPanel3 = new HorizontalPanel();
		hPanel3.setSpacing(5);
		hPanel3.add(stockQuoteDisplay.getDate());

		final HorizontalPanel hPanel4 = new HorizontalPanel();
		hPanel4.setSpacing(5);
		hPanel4.add(stockQuoteDisplay.getDayHigh());
		hPanel4.add(stockQuoteDisplay.getDayLow());
		hPanel4.add(stockQuoteDisplay.getClose());

		final HorizontalPanel hPanel5 = new HorizontalPanel();
		hPanel5.setSpacing(5);
		hPanel5.add(stockQuoteDisplay.getVolume());
		hPanel5.add(stockQuoteDisplay.getAdjClose());

		final VerticalPanel vPanel2 = new VerticalPanel();
		vPanel2.add(hPanel3);
		vPanel2.add(hPanel4);
		vPanel2.add(hPanel5);

				
		// Allow 4 pixels of spacing between each cell
		dock.setSpacing(4);

		/*
		 * Center each component horizontally within each cell for each
		 * component added after this call. A shortcut to calling
		 * dock.setCellHorizontalAlignment() for each cell.
		 */
		dock.setHorizontalAlignment(DockPanel.ALIGN_CENTER);

		// Add text widgets all around
		dock.add(vPanel1, DockPanel.NORTH);
		dock.add(vPanel2, DockPanel.SOUTH);
		// dock.add(new HTML("This is the east component"), DockPanel.EAST);
		// dock.add(new HTML("This is the west component"), DockPanel.WEST);
		// dock.add(new HTML("This is the <i>second</i> north component"),
		// DockPanel.NORTH);
		// dock.add(new HTML("This is the <i>second</i> south component"),
		// DockPanel.SOUTH);

		// Add scrollable text in the center
		contents = new HTML("This is a <code>ScrollPanel</code> contained at "
				+ "the center of a <code>DockPanel</code>.  " + "By putting some fairly large contents "
				+ "in the middle and setting its size explicitly, it becomes a "
				+ "scrollable area within the page, but without requiring the use of " + "an IFRAME.<br><br>"
				+ "Here's quite a bit more meaningless text that will serve primarily "
				+ "to make this thing scroll off the bottom of its visible area.  "
				+ "Otherwise, you might have to make it really, really small in order "
				+ "to see the nifty scroll bars!");
		ScrollPanel scroller = new ScrollPanel(contents);
		// scroller.setSize("400px", "100px");
		dock.add(scroller, DockPanel.CENTER);
		
		//add listeners
		addSuggestBoxListeners();
		
		showButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {				
				eventBus.fireEventFromSource(event, showButton);
			}
		});
		
		importButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEventFromSource(event, importButton);
			}
		});
		
		//import symbols for suggestboxes
		importSymbols();
		
	}
	
	private void updateChart() {
		GChart chart = new GChart();
		chart.setChartTitle(this.chartData.getTitle());
		chart.setChartSize(this.xChartSize, this.yChartSize);
		chart.addCurve();
		chart.getCurve().getSymbol().setSymbolType(SymbolType.LINE);
	    chart.getCurve().getSymbol().setHeight(0);
	    chart.getCurve().getSymbol().setWidth(0);
	    int xValues = 1;
	    for(ChartPoint chartPoint: this.chartData.getPoints()) {
	    	chart.getCurve().addPoint(xValues++, chartPoint.getValue());
	    }
	    chart.getCurve().setLegendLabel(this.chartData.getLegendLabel());
	    chart.getXAxis().setAxisLabel(this.chartData.getxAxisTitle());
	    chart.getYAxis().setAxisLabel(this.chartData.getyAxisTitle());
	    this.contents = chart.asWidget();
	}
	
	private void addSuggestBoxListeners() {
		name.addSelectionHandler(new SelectionHandler<Suggestion>() {
			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				String myName = event.getSelectedItem().getReplacementString();
				for(Symbol mySymbol: symbols) {
					if(myName != null && myName.equalsIgnoreCase(mySymbol.getName())) {
						symbol.setText(mySymbol.getSymbol());
						break;
					}
				}
			}
		});

		symbol.addSelectionHandler(new SelectionHandler<Suggestion>() {
			@Override
			public void onSelection(SelectionEvent<Suggestion> event) {
				String symbolStr = event.getSelectedItem().getReplacementString();
				for(Symbol mySymbol: symbols) {
					if(symbolStr != null && symbolStr.equalsIgnoreCase(mySymbol.getSymbol())) {
						name.setText(mySymbol.getName());
						break;
					}
				}
			}
		});
	}
	
	public void showDialogBox(String headerText, String style, String content) {
		// Create the popup dialog box		
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Label textToServerLabel = new Label();
		final Button closeButton = new Button("Close");
		closeButton.getElement().setId("closeButton");
		final VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Error message from server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		dialogBox.center();
		dialogBox.addCloseHandler(new CloseHandler<PopupPanel>() {
			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				dialogBox.hide();
			}
		});
		dialogBox.show();
	}
	
	private void importSymbols() {
		ChartingServiceAsync chartingService = GWT.create(ChartingService.class);
		chartingService.getSymbols(new AsyncCallback<List<Symbol>>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				showDialogBox("Remote Procedure Call - Failure", "serverResponseLabelError", SERVER_ERROR);								
			}

			public void onSuccess(List<Symbol> result) {
				symbols.clear();
				symbols.addAll(result);
				long symbolCount = 0;			
				for (Symbol mySymbol : result) {
					if (mySymbol.getName() != null || mySymbol.getName() != null) {
						nameOracle.add(mySymbol.getName());
						symbolOracle.add(mySymbol.getSymbol());
						symbolCount++;
					}
				}
				System.out.println("Symbols added: "+symbolCount);
			}
		});
	}
	
	private class StockQuoteDisplay {
		private final Label date;
		private final Label dayHigh;
		private final Label dayLow;
		private final Label close;
		private final Label volume;
		private final Label adjClose;		
		
		public StockQuoteDisplay() {
			StockQuote sq = new StockQuote();
			sq.setTime(new Date());
			sq.setAdjLast("-");
			sq.setCurrency("USD");
			sq.setDayHigh("-");
			sq.setDayLow("-");
			sq.setLast("-");
			sq.setName("-");
			sq.setSymbol("-");
			sq.setVolume("-");
			
			Date myDate = sq.getTime() == null ? new Date() : sq.getTime();
			this.date = new Label("Last Quote: " + myDate.toString());
			this.dayHigh = new Label("High: " + sq.getDayHigh());
			this.dayLow = new Label("Low: " + sq.getDayLow());
			this.close = new Label("Close: " + sq.getLast());
			this.volume = new Label("Volume: " + sq.getVolume());
			this.adjClose = new Label("Adj. Close: " + sq.getAdjLast());
		}

		public void setStockQuote(StockQuote sq) {
			date.setText("Last Quote: " + sq.getTime().toString());
			dayHigh.setText("High: " + sq.getDayHigh());
			dayLow.setText("Low: " + sq.getDayLow());
			close.setText("Close: " + sq.getLast());
			volume.setText("Volume: " + sq.getVolume());
			adjClose.setText("Adj. Close: " + sq.getAdjLast());
		}
		
		public Label getDate() {
			return date;
		}

		public Label getDayHigh() {
			return dayHigh;
		}

		public Label getDayLow() {
			return dayLow;
		}

		public Label getClose() {
			return close;
		}

		public Label getVolume() {
			return volume;
		}

		public Label getAdjClose() {
			return adjClose;
		}
	}
	
	@Override
	public void setName(String name) {		
		this.name.setValue(name, true);
	}

	@Override
	public void setSymbol(String symbol) {
		this.symbol.setValue(symbol, true);
	}

	@Override
	public void setTimeFrame(int itemNo) {
		this.timeframe.setItemSelected(itemNo, true);
	}

	@Override
	public void setPresenter(StockQuoteView.Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setStockQuote(StockQuote stockQuote) {
		this.stockQuoteDisplay.setStockQuote(stockQuote);		
	}

	@Override
	public void setSymbols(List<Symbol> symbols) {		
		long symbolCount = 0;			
		for (Symbol mySymbol : symbols) {
			if (mySymbol.getName() != null || mySymbol.getName() != null) {
				this.nameOracle.add(mySymbol.getName());
				this.symbolOracle.add(mySymbol.getSymbol());
				symbolCount++;
			}
		}
		System.out.println("Symbols added: "+symbolCount);
	}

	@Override
	public void setChartLink(String link) {
		HTML html = new HTML(link);
		this.contents = html;
	}	
	
	@Override
	public String getSymbol() {
		return this.getSymbol();
	}
	
	@Override
	public TimeFrame getTimeFrame() {
		TimeFrame myTimeFrame = TimeFrame.valueOf(this.timeframe.getItemText(this.timeframe.getSelectedIndex()));
		return myTimeFrame;
	}

	@Override
	public void setChartData(ChartData chartData) {
		this.chartData = chartData;
		updateChart();
	}

	public ChartDataParams getChartDataParams() {
		return new ChartDataParams(getSymbol(), getTimeFrame(), this.xChartSize, this.yChartSize);
	}

}
