package com.example.cinema.cinemapz;

import com.example.cinema.cinemapz.error.ErrorCode;

public class RestApiException extends RuntimeException {

	private ErrorCode errorCode;

	public RestApiException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
