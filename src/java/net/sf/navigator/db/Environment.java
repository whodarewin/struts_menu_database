package net.sf.navigator.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * keep the database config,get connection and close connection.
 * 
 * @author roger han
 * @date : 2014-1-6 下午02:39:53
 */

public class Environment {
	// ~ instance filed
	private String configLocation;

	private String className;

	public static String jdbcClassName;

	public static String url;

	public static String username;

	public static String password;

	public static String table;

	private String location;
	
	/**
	 * config if move the xml config to database
	 */
	private Boolean moveToDatabase = false;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getJdbcClassName() {
		return jdbcClassName;
	}

	public void setJdbcClassName(String jdbcClassName) {
		this.jdbcClassName = jdbcClassName;
	}

	public Boolean getMoveToDatabase() {
		return moveToDatabase;
	}

	public void setMoveToDatabase(Boolean moveToDatabase) {
		this.moveToDatabase = moveToDatabase;
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	public static void returnConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void init() {
		try {
			Class.forName(jdbcClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
