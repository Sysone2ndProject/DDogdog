package com.sysone.ddogdog.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getHttpStatus();
    String name();
    String getMessage();
}
