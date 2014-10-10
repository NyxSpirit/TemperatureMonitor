package se.anviken.temperature.monitor.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.DataTable;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TemperatureMonitor implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final QueryServiceAsync greetingService = GWT
			.create(QueryService.class);

	private final Messages messages = GWT.create(Messages.class);

	protected DataTable dataTable;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Create a callback to be called when the visualization API
		// has been loaded.
//		Runnable onLoadCallback = new Runnable() {
//			public void run() {
//				Panel panel = RootPanel.get();
//				greetingService.getDataTable("",
//						new AsyncCallback<ArrayList>() {
//							public void onFailure(Throwable caught) {
//								
//							}
//
//							public void onSuccess(ArrayList result) {
//								;
//								dataTable = DataTable.create();
//								dataTable.addColumn(ColumnType.STRING, "time");
//								dataTable.addColumn(ColumnType.NUMBER, "temp");
//								dataTable.addRows(result.size());
//								ListIterator iterator = result.listIterator();
//								while(iterator.hasNext()){
//									ArrayList columns = (ArrayList) iterator.next();
//									dataTable.setValue(0, 0, (String) columns.get(0));
//									dataTable.setValue(0, 1, (Float) columns.get(1));
//								}
//							}
//						});
//				
//				String test= "test";
//				// Create a pie chart visualization.
//				LineChart line = new LineChart(dataTable,createOptions());
//				panel.add(line);
//			}
//		};

		// Load the visualization api, passing the onLoadCallback to be called
		// when loading is done.
//		VisualizationUtils.loadVisualizationApi(onLoadCallback,
//				LineChart.PACKAGE);

		final Button sendButton = new Button(messages.sendButton());
		final TextBox nameField = new TextBox();
		nameField.setText(messages.tableField());
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);

		//dialogBox.setWidth("1200px");
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);

		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendToServer();
				}
			}
			private void sendToServer() {
				
				errorLabel.setText("");
				
				String textToServer = nameField.getText();
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.getTable(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}

//	private Options createOptions() {
//		Options options = Options.create();
//		options.setWidth(400);
//		options.setHeight(240);
//		options.setTitle("Utetemp");
//		return options;
//	}
//
//	
//
//	private AbstractDataTable createTable() {
//		DataTable data = DataTable.create();
//		data.addColumn(ColumnType.STRING, "Task");
//		data.addColumn(ColumnType.NUMBER, "Hours per Day");
//		data.addRows(3);
//		data.setValue(0, 0, "Work");
//		data.setValue(0, 1, 14);
//		data.setValue(1, 0, "Sleep");
//		data.setValue(1, 1, 10);
//		data.setValue(2, 0, "Other");
//		data.setValue(2, 1, 10);
//		return data;
//	}
}
