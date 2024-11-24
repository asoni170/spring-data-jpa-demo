package com.amit.exception;

public class MissingMandetoryFieldException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3765231230916029155L;

	public MissingMandetoryFieldException(String fieldName) {
		super(String.format("Mandatory field %s is missing to process this request", fieldName));
	}

}
