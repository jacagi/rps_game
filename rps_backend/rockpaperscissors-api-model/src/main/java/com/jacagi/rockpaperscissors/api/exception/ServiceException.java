package com.jacagi.rockpaperscissors.api.exception;

public class ServiceException extends Exception{

	private static final long serialVersionUID = 3853050551774679282L;

	public ServiceException() {
		super();
	}

	public ServiceException(String msg) {
		super(msg);
	}
	
	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
