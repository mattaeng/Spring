package com.mattaeng.mattaengapi.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeIfs {
	ALREADY_EXISTS_USER(HttpStatus.BAD_REQUEST.value(), "USER-01", "이미 존재하는 유저입니다."),
	NOT_EXISTS_ID(HttpStatus.BAD_REQUEST.value(), "USER-02", "존재하지 않는 ID입니다.");

	private final Integer httpStatusCode;
	private final String errorCode;
	private final String description;
}
