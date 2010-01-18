package gwt.test.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GWTCharting implements EntryPoint {

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	private final ChartingServiceAsync chartingService = GWT.create(ChartingService.class);
	
	public void onModuleLoad() {
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		final Button closeButton = new Button("Close");
		closeButton.getElement().setId("closeButton");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Error message from server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		
		final Label nameLabel = new Label("Name:");
		final Label symbolLabel = new Label("Symbol:");
		final Label timeFrameLabel = new Label("Timeframe:");
		final TextBox name = new TextBox();
		final TextBox symbol = new TextBox();
		final ListBox timeframe = new ListBox();
		timeframe.addItem("All", "All");
		timeframe.addItem("5 Years", "5Y");
		timeframe.addItem("2 Years", "2Y");
		timeframe.addItem("1 Year", "1Y");
		timeframe.addItem("6 Months", "6M");
		timeframe.addItem("1 Month", "1M");
		timeframe.addItem("14 Days", "14D");
		final Button showButton = new Button("Show");

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

		final VerticalPanel vPanel1 = new VerticalPanel();
		vPanel1.add(hPanel1);
		vPanel1.add(hPanel2);

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

		final Date myDate = sq.getTime() == null ? new Date() : sq.getTime();
		final Label date = new Label("Last Quote: " + myDate.toString());
		final Label dayHigh = new Label("High: " + sq.getDayHigh());
		final Label dayLow = new Label("Low: " + sq.getDayLow());
		final Label close = new Label("Close: " + sq.getLast());
		final Label volume = new Label("Volume: " + sq.getVolume());
		final Label adjClose = new Label("Adj. Close: " + sq.getAdjLast());

		final HorizontalPanel hPanel3 = new HorizontalPanel();
		hPanel3.setSpacing(5);
		hPanel3.add(date);

		final HorizontalPanel hPanel4 = new HorizontalPanel();
		hPanel4.setSpacing(5);
		hPanel4.add(dayHigh);
		hPanel4.add(dayLow);
		hPanel4.add(close);

		final HorizontalPanel hPanel5 = new HorizontalPanel();
		hPanel5.setSpacing(5);
		hPanel5.add(volume);
		hPanel5.add(adjClose);

		final VerticalPanel vPanel2 = new VerticalPanel();
		vPanel2.add(hPanel3);
		vPanel2.add(hPanel4);
		vPanel2.add(hPanel5);

		final DockPanel dock = new DockPanel();
		RootPanel.get().add(dock);
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
		final HTML contents = new HTML("This is a <code>ScrollPanel</code> contained at "
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

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			private void sendNameToServer() {
				showButton.setEnabled(false);
				chartingService.getLastStockQuote(symbol.getText(), new AsyncCallback<StockQuote>() {
					public void onFailure(Throwable caught) {
							// Show the RPC error message to the user
							dialogBox.setText("Remote Procedure Call - Failure");
							serverResponseLabel.addStyleName("serverResponseLabelError");
							serverResponseLabel.setHTML(SERVER_ERROR);
							dialogBox.center();

					}
					public void onSuccess(StockQuote result) {
						date.setText("Last Quote: " + result.getTime().toString());
						dayHigh.setText("High: " + result.getDayHigh());
						dayLow.setText("Low: " + result.getDayLow());
						close.setText("Close: " + result.getLast());
						volume.setText("Volume: " + result.getVolume());
						
					}
					
				});
				chartingService.getChart(symbol.getText(), TimeFrame.All, new AsyncCallback<String>() {				
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						contents.setHTML(result);
					}
				});
				showButton.setEnabled(true);
			}

			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		showButton.addClickHandler(handler);
		// sendButton.addClickHandler(handler);
		// nameField.addKeyUpHandler(handler);
	}

}
