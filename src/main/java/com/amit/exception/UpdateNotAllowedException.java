package com.amit.exception;

public class UpdateNotAllowedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UpdateNotAllowedException(String mismatchFilds) {
		super(String.format("%s missmatch, update not allowed", mismatchFilds));
	}

}
