package se.anviken.temperature.monitor.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.LineChart;
import com.google.gwt.visualization.client.visualizations.LineChart.Options;

public class OutdoorGUI extends FlowPanel{
	LineChart line;
	 Options options = Options.create();
	public OutdoorGUI() {
		super();
	    Runnable onLoadCallback = new Runnable() {
	      public void run() {
	       line = new LineChart();
	       line.setHeight("400px");
	        add(line);
	      }
	    };
	    VisualizationUtils.loadVisualizationApi(onLoadCallback, LineChart.PACKAGE);
	}

		  private AbstractDataTable createTable() {
			  DataTable data = DataTable.create();
			    data.addColumn(ColumnType.STRING, "X");
			    data.addColumn(ColumnType.NUMBER, "Chanel 1");
			    data.addColumn(ColumnType.NUMBER, "Channel 2");
			    data.addRows(2);
			    data.setValue(0, 0, "0");
			    data.setValue(0, 1, 0);
			    data.setValue(0, 2, 0);
			    data.setValue(1, 0, "1");
			    data.setValue(1, 1, 4);
			    data.setValue(1, 2, 1);
		    return data;
		  }
		public void fillChart() {
			line.draw( createTable(),options);
		}
		
}
