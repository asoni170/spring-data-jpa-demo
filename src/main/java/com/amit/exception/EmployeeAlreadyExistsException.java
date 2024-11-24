package com.amit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4972198057681574413L;

	public EmployeeAlreadyExistsException(String email) {
		super(String.format("Employee with provided email %s already exists in system.", email));
	}

}
