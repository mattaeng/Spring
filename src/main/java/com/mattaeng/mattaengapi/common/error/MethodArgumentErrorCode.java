package com.mattaeng.mattaengapi.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MethodArgumentErrorCode implements ErrorCodeIfs {
	INVALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST.value(), "ARGUMENT-01", "잘못된 요청값입니다."),
	INVALID_METHOD_ARGUMENT_TYPE(HttpStatus.BAD_REQUEST.value(), "ARGUMENT-02", "입력된 매개변수가 잘못된 타입입니다.");

	private final Integer httpStatusCode;
	private final String errorCode;
	private final String description;
}
