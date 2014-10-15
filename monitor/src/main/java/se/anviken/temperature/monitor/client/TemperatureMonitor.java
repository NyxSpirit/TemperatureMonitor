package se.anviken.temperature.monitor.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class TemperatureMonitor implements EntryPoint {

	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		TemperatureMonitorGUI gui = new TemperatureMonitorGUI();
		rootPanel.add(gui);
		
	}

}
