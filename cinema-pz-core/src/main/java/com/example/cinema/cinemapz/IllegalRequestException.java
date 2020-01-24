package com.example.cinema.cinemapz;

import com.example.cinema.cinemapz.error.ErrorCode;

public class IllegalRequestException extends RestApiException {

	public IllegalRequestException(ErrorCode errorCode) {
		super(errorCode);
	}

}
