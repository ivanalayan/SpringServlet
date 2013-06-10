package com.springservlet.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.springservlet.dao.CustomerDAO;
import com.springservlet.exceptions.LoginFailedException;
import com.springservlet.service.impl.CustomerServicesImpl;

public class CustomerServiceTest {

	private CustomerDAO customerDao;

	@Before
	public void init() {
		customerDao = Mockito.mock(CustomerDAO.class);
		Mockito.when(customerDao.hasCustomerOf("myusername", "mypassword")).thenReturn(true);
	}

	@Test
	public void loginWithExistingUser() throws LoginFailedException {

		CustomerServicesImpl service = new CustomerServicesImpl();
		service.setCustomerDAO(customerDao);
		service.validateAccount("myusername", "mypassword");

	}

	@Test(expected = LoginFailedException.class)
	public void loginWithNotExistingUser() throws LoginFailedException {

		CustomerServicesImpl service = new CustomerServicesImpl();
		service.setCustomerDAO(customerDao);
		service.validateAccount("username", "password");
	}
}
