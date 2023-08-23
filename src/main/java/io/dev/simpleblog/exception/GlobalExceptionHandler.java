package io.dev.simpleblog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static ResponseEntity<?> setResponse(HttpStatus status, BusinessException e) {
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(headers, status);
    }

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<?> handleBusinessException(BusinessException e) {
        log.debug("business exception occurred : {}", e.getMessage(), e);

        if (e instanceof NotFoundException)
            return setResponse(NOT_FOUND, e);

        return setResponse(INTERNAL_SERVER_ERROR, e);
    }


}
