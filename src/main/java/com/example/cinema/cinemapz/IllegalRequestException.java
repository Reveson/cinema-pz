package com.example.cinema.cinemapz;

public class IllegalRequestException extends RestApiException {

	public IllegalRequestException(ErrorCode errorCode) {
		super(errorCode);
	}

}
