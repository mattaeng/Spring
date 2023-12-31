package com.mattaeng.mattaengapi.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCodeIfs {
	OK(HttpStatus.OK.value(), "200", "성공"),
	PERMANENTLY_MOVED(HttpStatus.MOVED_PERMANENTLY.value(), "301", "MOVED PERMANENTLY"),
	TEMPORARILY_MOVED(HttpStatus.TEMPORARY_REDIRECT.value(), "302", "TEMPORARILY MOVED"),
	BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "400", "BAD REQUEST"),
	FORBIDDEN(HttpStatus.FORBIDDEN.value(), "403", "ACCESS DENIED"),
	SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "500", "SERVER ERROR"),
	NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), "512", "NULL POINT");

	private final Integer httpStatusCode;
	private final String errorCode;
	private final String description;
}
