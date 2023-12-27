package com.mattaeng.mattaengapi.common.exceptionHandler;

import java.nio.file.AccessDeniedException;
import java.rmi.AccessException;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.common.error.CommonErrorCode;
import com.mattaeng.mattaengapi.common.error.MethodArgumentErrorCode;
import com.mattaeng.mattaengapi.common.exception.ApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
public class CustomExceptionHandler {

	@ExceptionHandler(value = ApiException.class)
	public ResponseEntity<Api<Object>> apiException(ApiException apiException) {
		return new ExceptionResponseBuilder(apiException.getErrorCodeIfs(), apiException.getErrorDescription())
			.build();
	}

	@ExceptionHandler(value = AccessException.class)
	public ResponseEntity<Api<Object>> handleForbidden(AccessDeniedException e) {
		return new ExceptionResponseBuilder(CommonErrorCode.FORBIDDEN, e.getMessage())
			.build();
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Api<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return new ExceptionResponseBuilder(
			MethodArgumentErrorCode.INVALID_METHOD_ARGUMENT,
			e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
		).build();
	}

	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Api<Object>> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException e) {
		return new ExceptionResponseBuilder(
			MethodArgumentErrorCode.INVALID_METHOD_ARGUMENT_TYPE,
			e.getMessage()
		).build();
	}
}
