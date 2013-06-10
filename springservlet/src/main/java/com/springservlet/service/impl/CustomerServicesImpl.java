package com.springservlet.service.impl;

import com.springservlet.dao.CustomerDAO;
import com.springservlet.exceptions.LoginFailedException;
import com.springservlet.exceptions.UsernameAlreadyExistException;
import com.springservlet.models.Customer;
import com.springservlet.service.CustomerService;

public class CustomerServicesImpl implements CustomerService {

	private CustomerDAO customerDAO;

	@Override
	public Customer validateAccount(String username, String password)
			throws LoginFailedException {

		if (!customerDAO.hasCustomerOf(username, password))
			throw new LoginFailedException("Invalid login for username "
					+ username);
		return customerDAO.getCustomerDetailsAccountOf(username, password);
	}

	@Override
	public void addNewCustomer(Customer customer)
			throws UsernameAlreadyExistException {

		if (customerDAO.isUsernameExists(customer.getUsername()))
			throw new UsernameAlreadyExistException(customer.getUsername()
					+ " already exists!");
		customerDAO.addCustomer(customer);
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

}
