package com.mattaeng.mattaengapi.common.exceptionHandler;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.common.error.CommonErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)
public class GlobalExceptionHandler {

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Api<Object>> exception(Exception exception) {
		return new ExceptionResponseBuilder(CommonErrorCode.SERVER_ERROR, exception.getMessage())
			.build();
	}

}
