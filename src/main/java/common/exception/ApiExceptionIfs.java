package common.exception;

import common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {
    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();

}
