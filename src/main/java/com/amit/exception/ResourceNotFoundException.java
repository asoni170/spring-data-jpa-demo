package com.amit.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8717159418684767407L;

	public ResourceNotFoundException(String resourceName, String parameterName, String parameterValue) {
		super(String.format("%s does not exists in system with provided %s as %s", 
				resourceName, parameterName, parameterValue));
	}

}
