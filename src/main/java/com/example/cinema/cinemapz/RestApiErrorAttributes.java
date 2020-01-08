package com.example.cinema.cinemapz;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class RestApiErrorAttributes {

	private HttpStatus status;
	private LocalDateTime timestamp = LocalDateTime.now();
	private String message;
	private String debugMessage;

	RestApiErrorAttributes(HttpStatus status, Exception exception) {
		this(status, ErrorCode.UNEXPECTED_ERROR.name(), exception);
	}

	RestApiErrorAttributes(HttpStatus status, String message, Exception exception) {
		this.status = status;
		this.message = message;
		this.debugMessage = exception.getLocalizedMessage();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

}
