package com.springservlet.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.springservlet.dao.CustomerDAO;
import com.springservlet.models.Customer;
import com.springservlet.mysql.MySqlConnectionFactory;

public class CustomerDAOImpl implements CustomerDAO {

	private MySqlConnectionFactory connection;

	@Override
	public int addCustomer(Customer customer) {
		try {
			Connection connector = connection.getConnection();
			String sql = "INSERT INTO `OrderingSystem`.`Customer`(`firstname`,`lastname`,`username`,`password`,`birthday`,`address`,`registered_date`)";
			sql += "VALUES(?,?,?,?,?,?,curdate());";
			PreparedStatement statement = (PreparedStatement) connector
					.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getUsername());
			statement.setString(4, customer.getPassword());
			java.util.Date bday = customer.getBirthday();
			statement.setDate(5,
					new Date(bday.getYear(), bday.getMonth(), bday.getDay()));
			statement.setString(6, customer.getAddress());
			statement.execute();
			//statement.getResultSet().getInt(1);
			connector.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public Customer getCustomerDetailsAccountOf(String username, String password) {
		Customer cust = null;
		ResultSet queryResult;

		try {
			Connection connector = connection.getConnection();
			String sql = "SELECT * FROM `Customer` WHERE "
					+ "username = ? and password = ?";
			PreparedStatement statement = connector.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			queryResult = statement.executeQuery();
			queryResult.next();
			long custId = queryResult.getLong("id");
			String fname = queryResult.getString("firstname");
			String lname = queryResult.getString("lastname");
			Date bday = queryResult.getDate("birthday");
			String address = queryResult.getString("address");
			Date registeredDate = queryResult.getDate("registered_date");
			Date lastOrderDate = queryResult.getDate("last_ordered_date");
			cust = new Customer(custId, fname, lname, username, password, bday,
					registeredDate, lastOrderDate, address);
			connector.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cust;
	}

	public MySqlConnectionFactory getConnection() {
		return connection;
	}

	public void setConnection(MySqlConnectionFactory connection) {
		this.connection = connection;
	}

	@Override
	public boolean hasCustomerOf(String username, String password) {
		ResultSet queryResult;
		try {
			Connection connector = connection.getConnection();
			String sql = "SELECT COUNT(*) FROM `Customer` WHERE "
					+ "username = ? and password = ?";
			PreparedStatement statement = connector.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			queryResult = statement.executeQuery();
			queryResult.next();
			int count = queryResult.getInt(1);
			if (count == 0)
				return false;
			connector.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean isUsernameExists(String username) {
		ResultSet queryResult;
		try {
			Connection connector = connection.getConnection();
			String sql = "SELECT COUNT(*) FROM `Customer` WHERE "
					+ "username = ?";
			PreparedStatement statement = connector.prepareStatement(sql);
			statement.setString(1, username);
			queryResult = statement.executeQuery();
			queryResult.next();
			int count = queryResult.getInt(1);
			if (count == 0)
				return false;
			connector.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
