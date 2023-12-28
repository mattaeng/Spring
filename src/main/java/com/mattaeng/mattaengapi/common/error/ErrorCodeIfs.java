package com.mattaeng.mattaengapi.common.error;

public interface ErrorCodeIfs {
	Integer getHttpStatusCode();

	String getErrorCode();

	String getDescription();
}
