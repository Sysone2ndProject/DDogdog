package com.sysone.ddogdog.common.exception;

public class NoAvailableRoomsException extends RuntimeException {
    private final ErrorCode errorCode;

    public NoAvailableRoomsException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}