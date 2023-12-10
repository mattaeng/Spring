package common.excepationhandler;

import common.api.Api;
import common.error.ErrorCode;
import common.exception.ApiException;
import java.nio.file.AccessDeniedException;
import java.rmi.AccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
public class CustomExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>> apiException(ApiException apiException){
        log.error("",apiException);

        var errorCode = apiException.getErrorCodeIfs();
        return ResponseEntity
            .status(errorCode.getHttpStatusCode())
            .body(
                Api.error(errorCode, apiException.getErrorDescription())
            );
    }
    @ExceptionHandler(value = AccessException.class)
    public ResponseEntity<Api<Object>> handleForbidden(AccessDeniedException e){
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(Api.error(ErrorCode.FORBIDDEN));
    }
}
