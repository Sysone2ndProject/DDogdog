package com.sysone.ddogdog.owner.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class OwnerException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    public OwnerException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpstatus() {
        return httpStatus;
    }
}