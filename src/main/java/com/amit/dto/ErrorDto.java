package com.amit.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ErrorResponse", description = "Schema to hold error response details")
public class ErrorDto {

	@Schema(name = "ErrorCode", description = "Errorcode representing error happend")
	private String errorCode;
	
	@Schema(name = "ErrorMessage", description = "Error message representing error happend")
	private String errorMessage;
	
	@Schema(name = "ErroTime", description = "Time representing when error occured")
	private LocalDateTime errorTimeStamp;

	public String getErrorCode() {
		return errorCode;
	}

	public ErrorDto setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public ErrorDto setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	public LocalDateTime getErrorTimeStamp() {
		return errorTimeStamp;
	}

	public ErrorDto setErrorTimeStamp(LocalDateTime errorTimeStamp) {
		this.errorTimeStamp = errorTimeStamp;
		return this;
	}

}
