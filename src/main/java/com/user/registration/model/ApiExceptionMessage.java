package com.user.registration.model;

public class ApiExceptionMessage {
	private String message;
	
	public ApiExceptionMessage() {
    }
	
	public ApiExceptionMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
