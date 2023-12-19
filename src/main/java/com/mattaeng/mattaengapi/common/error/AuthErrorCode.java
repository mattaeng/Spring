package com.mattaeng.mattaengapi.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCodeIfs {
	NOT_EXIST_USERID(HttpStatus.BAD_REQUEST.value(), "AUTH-01", "존재하지 않는 이메일입니다."),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED.value(), "AUTH-02", "잘못된 비밀번호입니다."),
	EMPTY_AUTHORIZATION_HEADER(HttpStatus.BAD_REQUEST.value(), "AUTH-03", "Authorization 헤더가 존재하지 않습니다."),
	INVALID_AUTHORIZATION_TYPE(HttpStatus.BAD_REQUEST.value(), "AUTH-04", "잘못된 Authorization 타입입니다."),
	EMPTY_TOKEN(HttpStatus.BAD_REQUEST.value(), "AUTH-05", "토큰이 존재하지 않습니다."),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "AUTH-06", "유효하지 않은 토큰입니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED.value(), "AUTH-07", "만료된 토큰입니다.");

	private final Integer httpStatusCode;
	private final String errorCode;
	private final String description;
}
