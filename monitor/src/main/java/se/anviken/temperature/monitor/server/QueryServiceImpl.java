package se.anviken.temperature.monitor.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import se.anviken.temperature.monitor.client.QueryService;
import se.anviken.temperature.monitor.shared.HTMLUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class QueryServiceImpl extends RemoteServiceServlet implements
		QueryService {

	public String getTable(String input) {
		String out = "";

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("kardyll");
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("temperature");

		Connection conn;
		try {
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			conn.setSchema("temperature");
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM " + input + " limit 25;");
			//"SELECT * FROM temperature.temperatures where temperatures.sensor_id = 14 and date(temperatures.temp_timestamp) = date(now()) limit 25;"
			out = HTMLUtils.rsToHTML(rs);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			out = e.getLocalizedMessage();
		}
		return out;
	}
}
