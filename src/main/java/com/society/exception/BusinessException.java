package com.society.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class BusinessException extends Exception {

	private HttpStatus httpStatus;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public BusinessException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

}
