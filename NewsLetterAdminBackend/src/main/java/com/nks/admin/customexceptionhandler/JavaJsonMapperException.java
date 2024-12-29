package com.nks.admin.customexceptionhandler;

import java.util.HashMap;

public final class JavaJsonMapperException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4009640040931252363L;
	private final String message;
	private final HashMap<String, String> errorDetails;
	
	@SuppressWarnings("unchecked")
	public JavaJsonMapperException(HashMap<String, String> errorDetails) {
		this.message = "Invalid input stream";
		this.errorDetails = (HashMap<String, String>) errorDetails.clone();
	}
	
	public JavaJsonMapperException(String message) {
		this.message = message;
		this.errorDetails = null;
	}

	public String getMessage() {
		return message;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getErrorDetails() {
		return (HashMap<String, String>) errorDetails.clone();
	}

}
