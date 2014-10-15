package se.anviken.temperature.monitor.shared;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;

public class HTMLUtils {
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 *
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	public String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
	
	/**
	 * Converts a resultset to HTML table
	 * @param rs The Resultset to convert
	 * @return The HTML code for the ResultSet in form of a table
	 */
	public static String rsToHTML(ResultSet rs) {
		String html = "";
		try {
			html = html + "<P ALIGN='center'><TABLE BORDER=1>";
			ResultSetMetaData rsmd;

			rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();
			// table header
			html = html + "<TR>";
			for (int i = 0; i < columnCount; i++) {
				html = html + "<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>";
			}
			html = html + "</TR>";
			// the data
			while (rs.next()) {
				html = html + "<TR>";
				for (int i = 0; i < columnCount; i++) {
					html = html + "<TD>" + rs.getString(i + 1) + "</TD>";
				}
				html = html + "</TR>";
			}
			html = html + "</TABLE></P>";
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return html;
	}
	
	private DataTable jsonStringToDataTable(String result) {
		DataTable dataTable;
		JSONValue value = JSONParser.parseStrict(result);
		JSONObject jsonObject = value.isObject();
		JSONArray jsonArray = jsonObject.get("graph_data").isArray();

		dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.DATETIME, "time");
		dataTable.addColumn(ColumnType.NUMBER, "temp");
		int size = jsonArray.size();
		dataTable.addRows(size);
		for (int i = 0; i < size; i++) {
			JSONObject tempLog = jsonArray.get(i).isObject();
			dataTable.setValue(i, 0, tempLog.get("time").toString());
			dataTable.setValue(i, 1, tempLog.get("temperature").isNumber()
					.doubleValue());
		}
		// line.draw(dataTable,createOptions());
		return dataTable;

	}
}
