package com.springservlet.dao;

import org.junit.Before;
import org.junit.Test;

import com.springservlet.models.Customer;

import static org.mockito.Mockito.*;
import static junit.framework.Assert.*;



public class CustomerDAOTest {

	
	private CustomerDAO customerDAO;
	
	@Before
	public void init(){
		customerDAO = mock(CustomerDAO.class);
		when(customerDAO.hasCustomerOf("myusername", "mypassword")).thenReturn(true);
		
	}
	
	@Test
	public void hasCustomerOf(){
		
		assertEquals(customerDAO.hasCustomerOf("myusername","mypassword"), true);
	}
	
	@Test
	public void sucessfullAdd(){
		
		Customer customer = new Customer();
		customer.setUsername("username");
		when(customerDAO.addCustomer(customer)).thenReturn(2);
		int id = customerDAO.addCustomer(customer);
		assertNotSame(id, -1);
	}
	
	@Test
	public void failedAdd(){
		
		Customer customer = new Customer();
		customer.setUsername("username");
		when(customerDAO.addCustomer(customer)).thenReturn(-1);
		int id = customerDAO.addCustomer(customer);
		assertEquals(id, -1);
	}
	
	@Test
	public void hasNotCustomerOf(){
		assertEquals(customerDAO.hasCustomerOf("username","password"), false);
	}
}
