package com.mattaeng.mattaengapi.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCodeIfs {
	NOT_EXIST_EMAIL(HttpStatus.BAD_REQUEST.value(), "AUTH-01", "존재하지 않는 이메일입니다."),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED.value(), "AUTH-02", "잘못된 비밀번호입니다.");
	private final Integer httpStatusCode;
	private final String errorCode;
	private final String description;
}
