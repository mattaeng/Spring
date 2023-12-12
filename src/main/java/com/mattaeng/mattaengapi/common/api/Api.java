package com.mattaeng.mattaengapi.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mattaeng.mattaengapi.common.error.ErrorCodeIfs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Api<T> {

	@JsonUnwrapped
	private T body;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Error error;

	private Api(T body) {
		this.body = body;
	}

	private Api(Error error) {
		this.error = error;
	}

	public static <T> Api<T> ok(T data) {
		return new Api<>(data);
	}

	public static Api<Object> error(ErrorCodeIfs errorCode) {
		Error error = new Error(errorCode.getErrorCode(), errorCode.getDescription());
		return new Api<>(error);
	}

	public static Api<Object> error(ErrorCodeIfs errorCode, String message) {
		Error error = new Error(errorCode.getErrorCode(), message);
		return new Api<>(error);
	}

	@Getter
	@AllArgsConstructor
	private static class Error {
		private Integer errorCode;
		private String description;
	}
}
