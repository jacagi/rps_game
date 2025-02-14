package com.jacagi.rockpaperscissors.api.exception;

public class IncorrectAuthenticationException extends Exception{

	private static final long serialVersionUID = 3853050551774679282L;

	public IncorrectAuthenticationException() {
		super();
	}

	public IncorrectAuthenticationException(String msg) {
		super(msg);
	}
	
	public IncorrectAuthenticationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public IncorrectAuthenticationException(Throwable cause) {
		super(cause);
	}
	
}
