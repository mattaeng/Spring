package com.mattaeng.mattaengapi.common.exception;

import com.mattaeng.mattaengapi.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {
    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();

}
