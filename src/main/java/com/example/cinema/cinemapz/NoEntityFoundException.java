package com.example.cinema.cinemapz;

public class NoEntityFoundException extends RestApiException {

	public NoEntityFoundException(ErrorCode errorCode) {
		super(errorCode);
	}

}
