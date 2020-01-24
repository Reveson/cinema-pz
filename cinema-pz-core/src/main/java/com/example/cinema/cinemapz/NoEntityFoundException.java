package com.example.cinema.cinemapz;

import com.example.cinema.cinemapz.error.ErrorCode;

public class NoEntityFoundException extends RestApiException {

	public NoEntityFoundException(ErrorCode errorCode) {
		super(errorCode);
	}

}
