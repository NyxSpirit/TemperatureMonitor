package se.anviken.temperature.monitor.client.gui;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.shape.Circle;
import org.vaadin.gwtgraphics.client.shape.Rectangle;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.LineChart;
import com.google.gwt.visualization.client.visualizations.LineChart.Options;

public class HeatingGUI extends FlowPanel{
	LineChart line;
	 Options options = Options.create();
	public HeatingGUI() {
		super();
	    Runnable onLoadCallback = new Runnable() {
	      public void run() {
	       line = new LineChart();
	       line.setHeight("400px");
	        add(line);
	      }
	    };
	    DrawingArea canvas = new DrawingArea(400, 400);
	    add(canvas);

	    // After that you can start drawing. 
	    // For example, the following code draws a red circle :
	    Circle circle = new Circle(10, 10, 50);
	    circle.setFillColor("red");
	    circle.setStrokeColor("blue");
	    canvas.add(circle);
	    Rectangle rectanglefill = new Rectangle(103, 103, 94, 194);
	    rectanglefill.setFillColor("red");
	    canvas.add(rectanglefill);
	    
	    Rectangle rectangle = new Rectangle(100, 100, 100, 200);
	    rectangle.setRoundedCorners(10);
	    rectangle.setStrokeWidth(6);
	    rectangle.setFillOpacity(0);
	    canvas.add(rectangle);
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
