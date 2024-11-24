package com.amit.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
	
	public ResourceAlreadyExistsException(String resourceName, String parameterName, String parameterValue) {
		super(String.format("%s already exists in system with provided %s as %s", 
				resourceName, parameterName, parameterValue));
	}

}
