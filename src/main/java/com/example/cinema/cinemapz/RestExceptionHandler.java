package com.example.cinema.cinemapz;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cinema.cinemapz.error.ErrorCode;
import com.example.cinema.cinemapz.error.RestApiErrorAttributes;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//TODO test and make messages nicer


	@ExceptionHandler(NoEntityFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(NoEntityFoundException exception) {
		RestApiErrorAttributes apiError = new RestApiErrorAttributes(NOT_FOUND, exception.getErrorCode().name(), exception);
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(IllegalRequestException.class)
	protected ResponseEntity<Object> handleIllegalRequest(IllegalRequestException exception) {
		RestApiErrorAttributes apiError = new RestApiErrorAttributes(BAD_REQUEST, exception.getErrorCode().name(), exception);
		return buildResponseEntity(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		String errorMessage = String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL());
		RestApiErrorAttributes apiError = new RestApiErrorAttributes(BAD_REQUEST, errorMessage, ex);
		return buildResponseEntity(apiError);
	}
	/**
	 * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
	 *
	 * @param ex      MissingServletRequestParameterException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the RestApiErrorAttributes object
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";
		return buildResponseEntity(new RestApiErrorAttributes(BAD_REQUEST, error, ex));
	}


	/**
	 * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
	 *
	 * @param ex      HttpMediaTypeNotSupportedException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the RestApiErrorAttributes object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
		return buildResponseEntity(new RestApiErrorAttributes(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
	}

	/**
	 * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
	 *
	 * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the RestApiErrorAttributes object
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		RestApiErrorAttributes apiError = new RestApiErrorAttributes(BAD_REQUEST, "12345", ex);
		return buildResponseEntity(apiError);
	}

	/**
	 * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
	 *
	 * @param ex the ConstraintViolationException
	 * @return the RestApiErrorAttributes object
	 */
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(
			javax.validation.ConstraintViolationException ex) {
		RestApiErrorAttributes apiError = new RestApiErrorAttributes(BAD_REQUEST, "qwe", ex);
		return buildResponseEntity(apiError);
	}

	/**
	 * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
	 *
	 * @param ex      HttpMessageNotReadableException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the RestApiErrorAttributes object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
//		log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
		String error = "Malformed JSON request";
		return buildResponseEntity(new RestApiErrorAttributes(HttpStatus.BAD_REQUEST, error, ex));
	}

	/**
	 * Handle HttpMessageNotWritableException.
	 *
	 * @param ex      HttpMessageNotWritableException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the RestApiErrorAttributes object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Error writing JSON output";
		return buildResponseEntity(new RestApiErrorAttributes(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {
		if (ex.getCause() instanceof ConstraintViolationException) {
			return buildResponseEntity(new RestApiErrorAttributes(HttpStatus.CONFLICT, "Database error", ex));
		}
		return buildResponseEntity(new RestApiErrorAttributes(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		RestApiErrorAttributes RestApiErrorAttributes = new RestApiErrorAttributes(BAD_REQUEST, ErrorCode.BAD_REQUEST.name(), ex);
		return buildResponseEntity(RestApiErrorAttributes);
	}

	private ResponseEntity<Object> buildResponseEntity(RestApiErrorAttributes apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
