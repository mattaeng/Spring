package com.mattaeng.mattaengapi.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeIfs {
	NOT_EXISTS_ID(HttpStatus.BAD_REQUEST.value(), "USER-01", "존재하지 않는 ID입니다.");

	private final Integer httpStatusCode;
	private final String errorCode;
	private final String description;
}
