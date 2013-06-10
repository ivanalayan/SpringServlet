package com.springservlet.exceptions;

public class UsernameAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExistException() {
		super();
	}

	public UsernameAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameAlreadyExistException(String message) {
		super(message);
	}

	public UsernameAlreadyExistException(Throwable cause) {
		super(cause);
	}

	
}
