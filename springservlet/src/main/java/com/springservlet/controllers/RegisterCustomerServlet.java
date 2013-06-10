package com.springservlet.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springservlet.exceptions.UsernameAlreadyExistException;
import com.springservlet.models.Customer;
import com.springservlet.service.CustomerService;
import com.springservlet.service.impl.CustomerServicesImpl;

public class RegisterCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService;
	
	public RegisterCustomerServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		customerService = (CustomerService) request.getServletContext().getAttribute("CustomerService");
		
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String username = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		String address = request.getParameter("address").trim();
		SimpleDateFormat formatBirthday = new SimpleDateFormat("yyyy-mm-dd");
		Date birthday = null;
		try {
			birthday = formatBirthday.parse(request.getParameter("birthday")
					.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setUsername(username);
		customer.setPassword(password);
		customer.setBirthday(birthday);
		customer.setAddress(address);
		
		try {
			customerService.addNewCustomer(customer);
			HttpSession session = request.getSession();
			session.setAttribute("account", customer);
			RequestDispatcher rd = request
					.getRequestDispatcher("/login");
				rd.forward(request, response);
		} catch (UsernameAlreadyExistException message) {
			request.setAttribute("errorMessages", message.getMessage());
			RequestDispatcher rd = request
					.getRequestDispatcher("/registration");
				rd.forward(request, response);
		} 

	}

}
