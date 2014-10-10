package se.anviken.temperature.monitor.shared;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLConnection {

	private Connection conn;
	private  Statement stmt;
		
	/**Creates a connection to the MySQL database configured in se.anviken.temperature.monitor.props file
	 * @throws SQLException
	 * @throws IOException
	 */
	public MySQLConnection() throws SQLException, IOException {
		String prop = System.getProperty("se.anviken.temperature.monitor.props");
		FileInputStream fis = new FileInputStream(prop);
		Properties props = new Properties();
		props.load(fis);
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(props.getProperty("mysqluser"));
		dataSource.setPassword(props.getProperty("mysqlpassword"));
		dataSource.setServerName(props.getProperty("mysqlhost"));
		dataSource.setDatabaseName(props.getProperty("mysqldatabase"));
		conn = dataSource.getConnection();
		conn.setSchema(props.getProperty("mysqldatabase"));
		stmt = conn.createStatement();
	}
	
	/**
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public  ResultSet executeQuery(String query) throws SQLException{
		return stmt.executeQuery(query);
	}
	
	/**
	 * Performs a select on a table and returns the result as a ResultSet
	 * @param table Table to query
	 * @return Result from query as ResultSet
	 * @throws SQLException
	 */
	public  ResultSet selectFromTable(String table) throws SQLException {
		return selectFromTable(table,null, 0);
	}
	/**
	 * Performs a select on a table and returns the result as a ResultSet
	 * @param table Table to query
	 * @param limit Limit number of rows in result
	 * @return  Result from query as ResultSet
	 * @throws SQLException
	 */
	public ResultSet selectFromTable(String table, int limit) throws SQLException {
		return selectFromTable(table,null, limit);
	}
	/**
	 * Performs a select on a table and returns the result as a ResultSet
	 * @param table Table to query
	 * @param where Where statement for query (without where)
	 * @return Result from query as ResultSet
	 * @throws SQLException
	 */
	public  ResultSet selectFromTable(String table,String where) throws SQLException {
		return selectFromTable(table,where, 0);
		
	}
	/**
	 * Performs a select on a table and returns the result as a ResultSet
	 * @param table Table to query
	 * @param where Where statement for query (without where)
	 * @param limit Limit number of rows in result
	 * @return Result from query as ResultSet
	 * @throws SQLException
	 */
	public  ResultSet selectFromTable(String table,String where, int limit) throws SQLException {
		
		String select = "SELECT * FROM " + table + " ";
		if(where != null){
			select = select +"where "+ where + " ";
		}
		if(limit!=0) 
			select = select +" limit "+limit;
		return executeQuery(select+";");
		
	}
	/**
	 * @throws SQLException
	 */
	public void close() throws SQLException{
		conn.close();
		stmt.close();
	}
}
