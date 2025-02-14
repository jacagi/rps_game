package com.jacagi.rockpaperscissors.api.exception;

public class BusinessException extends Exception{

	private static final long serialVersionUID = 3853050551774679282L;

	public BusinessException() {
		super();
	}

	public BusinessException(String msg) {
		super(msg);
	}
	
	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public BusinessException(Throwable cause) {
		super(cause);
	}
}
