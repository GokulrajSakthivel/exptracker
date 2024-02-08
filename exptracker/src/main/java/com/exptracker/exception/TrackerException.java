package com.exptracker.exception;

public class TrackerException extends RuntimeException {

	private String errorMessage;

	
	public TrackerException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	@Override
	public String getMessage() {
		return this.errorMessage;
	}

}
