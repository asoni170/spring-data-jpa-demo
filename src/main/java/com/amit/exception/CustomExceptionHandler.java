package com.amit.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amit.dto.ErrorDto;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<ErrorDto> handelEmployeeAlreadyExistException(EmployeeAlreadyExistsException ex) {

		ErrorDto error = new ErrorDto();
		error.setErrorCode(HttpStatus.BAD_REQUEST.toString()).setErrorMessage(ex.getMessage())
				.setErrorTimeStamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDto> handelAllException(Exception ex) {

		ErrorDto error = new ErrorDto();
		error.setErrorCode(HttpStatus.EXPECTATION_FAILED.toString()).setErrorMessage(ex.getMessage())
				.setErrorTimeStamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(error);
	}
	
	@ExceptionHandler(NoEmployeeFoundException.class)
	public ResponseEntity<ErrorDto> handelNoEmployeeFoundException(NoEmployeeFoundException ex) {

		ErrorDto error = new ErrorDto();
		error.setErrorCode(HttpStatus.NOT_FOUND.toString()).setErrorMessage(ex.getMessage())
				.setErrorTimeStamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(UpdateNotAllowedException.class)
	public ResponseEntity<ErrorDto> handelUpdateNotAllowedException(UpdateNotAllowedException ex) {

		ErrorDto error = new ErrorDto();
		error.setErrorCode(HttpStatus.UNPROCESSABLE_ENTITY.toString()).setErrorMessage(ex.getMessage())
				.setErrorTimeStamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> allErrors = ex.getAllErrors();
		
		Map<String, String> errorMap = new HashMap<String, String>();
		
		for(ObjectError error : allErrors) {
			String parameterName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			
			errorMap.put(parameterName, errorMessage);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
	}

}
