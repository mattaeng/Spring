package com.mattaeng.mattaengapi.common.exceptionHandler;

import org.springframework.http.ResponseEntity;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.common.error.ErrorCodeIfs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ExceptionResponseBuilder {

	private final ErrorCodeIfs errorCode;

	private final String description;

	public ResponseEntity<Api<Object>> build() {
		log.error("Handling Exception: " + description);
		return ResponseEntity
			.status(this.errorCode.getHttpStatusCode())
			.body(Api.error(this.errorCode, description));
	}
}
