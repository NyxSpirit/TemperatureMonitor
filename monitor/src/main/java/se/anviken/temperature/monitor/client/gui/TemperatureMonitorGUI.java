package se.anviken.temperature.monitor.client.gui;

import se.anviken.temperature.monitor.client.Messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;

public class TemperatureMonitorGUI extends Composite {

	private final Messages i18n = GWT.create(Messages.class);
	private DecoratedTabPanel decoratedStackPanel;
	private HeatingGUI heating;
	private OutdoorGUI outdoor;
	private FlowPanel upstairs;
	private FlowPanel downstairs;
	public TemperatureMonitorGUI() {
		decoratedStackPanel = new DecoratedTabPanel();
		
		initWidget(decoratedStackPanel);
		decoratedStackPanel.setSize("90%", "90%");
		decoratedStackPanel.setStyleName("center");
		
		outdoor = new OutdoorGUI();
		outdoor.setSize("100%", "100%");
		
		heating = new HeatingGUI();
		heating.setSize("100%", "100%");
		
		upstairs = new FlowPanel();
		upstairs.setSize("100%", "100%");
		
		downstairs = new FlowPanel();
		downstairs.setSize("100%", "100%");
		
		decoratedStackPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			public void onSelection(SelectionEvent<Integer> event) {
				if (event.getSelectedItem() == decoratedStackPanel.getWidgetIndex(outdoor)){
					outdoor.fillChart();
				}else if (event.getSelectedItem() == decoratedStackPanel.getWidgetIndex(upstairs)){
					
				}else if (event.getSelectedItem() == decoratedStackPanel.getWidgetIndex(downstairs)){
					
				}
			}
		});
		
		
		decoratedStackPanel.add(upstairs, i18n.upstairs(), false);
		decoratedStackPanel.add(downstairs,i18n.downstairs(),false);
		decoratedStackPanel.add(outdoor, i18n.outsidetemperature(), false);
		decoratedStackPanel.add(heating, i18n.heating(), false);
	}
}
