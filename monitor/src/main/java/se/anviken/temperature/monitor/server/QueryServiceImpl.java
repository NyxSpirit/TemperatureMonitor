package se.anviken.temperature.monitor.server;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import se.anviken.temperature.monitor.client.QueryService;
import se.anviken.temperature.monitor.shared.HTMLUtils;
import se.anviken.temperature.monitor.shared.MySQLConnection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class QueryServiceImpl extends RemoteServiceServlet implements
		QueryService {

	public String getTableAsHTML(String input) {
		String out = "";		
		try {			
			MySQLConnection conn = new MySQLConnection();
			ResultSet rs = conn.selectFromTable(input,25);
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
