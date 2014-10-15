package se.anviken.temperature.monitor.server;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import se.anviken.temperature.monitor.client.GetGraphDataService;
import se.anviken.temperature.monitor.shared.MySQLConnection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GetGraphDataServiceImpl extends RemoteServiceServlet implements
		GetGraphDataService {

	public String getData(String input) {

		JSONObject responseObj = new JSONObject();
		List<JSONObject> allTemps = new LinkedList<JSONObject>();

		try {
			MySQLConnection conn = new MySQLConnection();
			ResultSet rs = conn.executeQuery("SELECT * FROM temperature.temperatures where temperatures.sensor_id = 14 and date(temperatures.temp_timestamp) = date(now())");
			rs.first();
			do {
				JSONObject tempLog = new JSONObject();
				tempLog.put("time",
						rs.getString("temp_timestamp"));
				tempLog.put("temperature",
						rs.getDouble("temperature"));
				allTemps.add(tempLog);
			}while(rs.next());
			responseObj.put("graph_data", allTemps);
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return responseObj.toString();
	}
}
