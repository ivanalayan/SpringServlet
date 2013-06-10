package com.springservlet.service;

import com.springservlet.exceptions.LoginFailedException;
import com.springservlet.exceptions.UsernameAlreadyExistException;
import com.springservlet.models.Customer;

public interface CustomerService {

	public Customer validateAccount(String username, String password)throws LoginFailedException;
	public void addNewCustomer(Customer customer) throws UsernameAlreadyExistException;

}
