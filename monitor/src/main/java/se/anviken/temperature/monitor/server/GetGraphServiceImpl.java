package se.anviken.temperature.monitor.server;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import se.anviken.temperature.monitor.client.GetGraphDataService;
import se.anviken.temperature.monitor.shared.HTMLUtils;
import se.anviken.temperature.monitor.shared.MySQLConnection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GetGraphServiceImpl extends RemoteServiceServlet implements
GetGraphDataService {

	public String getData(String input) {
		String out = "";		
		try {			
			MySQLConnection conn = new MySQLConnection();
			ResultSet rs = conn.selectFromTable(input,25);
			//"SELECT * FROM temperature.temperatures where temperatures.sensor_id = 14 and date(temperatures.temp_timestamp) = date(now()) limit 25;"
			out = HTMLUtils.rsToHTML(rs);
			rs.close();
			conn.close();
		} catch (SQLException e) {
			out = e.getLocalizedMessage();
		} catch (IOException e) {
			out = e.getLocalizedMessage();
		}
		return out;
	}
}
