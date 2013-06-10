package com.springservlet.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springservlet.exceptions.LoginFailedException;
import com.springservlet.models.Customer;
import com.springservlet.service.CustomerService;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		CustomerService customerService = (CustomerService) req
				.getServletContext().getAttribute("CustomerService");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		try {
			Customer customer = customerService.validateAccount(username,
					password);
			HttpSession session = req.getSession();
			session.setAttribute("account", customer);
			RequestDispatcher rd = req
					.getRequestDispatcher("customer/welcome.jsp");
			rd.forward(req, resp);
		} catch (LoginFailedException e) {
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			req.setAttribute("errorMessages", e.getMessage());
			rd.forward(req, resp);
		}

	}

}
