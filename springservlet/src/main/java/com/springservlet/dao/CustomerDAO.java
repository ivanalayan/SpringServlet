package com.springservlet.dao;

import com.springservlet.models.Customer;
import javax.sql.DataSource;

public interface CustomerDAO {

	public int addCustomer(Customer customer);

	public Customer getCustomerDetailsAccountOf(String username, String password);

	public boolean hasCustomerOf(String username, String password);

	public boolean isUsernameExists(String username);
}
