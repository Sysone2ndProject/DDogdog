package com.sysone.ddogdog.common.exception;

import lombok.Getter;

@Getter
public class NoDataFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public NoDataFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
