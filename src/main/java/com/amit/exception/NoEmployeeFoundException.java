package com.amit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoEmployeeFoundException extends RuntimeException {
	
	public NoEmployeeFoundException() {
		super("No employee record found for provided search criteria.");
	}

	public NoEmployeeFoundException(String resourcename, String parameterName, String parameterValue) {
		super(String.format("No %s found with provided %s as %s", resourcename, parameterName, parameterValue));
	}

}
