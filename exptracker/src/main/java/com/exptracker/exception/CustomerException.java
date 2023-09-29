package com.exptracker.exception;

public class CustomerException extends RuntimeException {

	private String errorMessage;

	
	public CustomerException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	@Override
	public String getMessage() {

		return this.errorMessage;
	}

}
