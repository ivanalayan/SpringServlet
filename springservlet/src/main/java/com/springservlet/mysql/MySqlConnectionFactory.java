package com.springservlet.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.springservlet.models.ConnectionProperties;

public class MySqlConnectionFactory {

	private Connection connector;
	private ConnectionProperties prop;

	public MySqlConnectionFactory() {

	}

	public Connection initializeConnection() throws IOException, SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connector = DriverManager.getConnection(getProp().getUrl(),
				getProp().getUser(), getProp().getPassword());
	}

	public Connection getConnection() {
		try {
			initializeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.connector;
	}

	public void closeConnection() throws SQLException {
		connector.close();
	}

	public ConnectionProperties getProp() {
		return prop;
	}

	public void setProp(ConnectionProperties prop) {
		this.prop = prop;
	}

}
