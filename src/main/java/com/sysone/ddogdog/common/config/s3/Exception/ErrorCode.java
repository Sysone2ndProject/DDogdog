package com.sysone.ddogdog.common.config.s3.Exception;

public enum ErrorCode {
    EMPTY_FILE_EXCEPTION("파일이 존재하지 않습니다", 400),
    NO_FILE_EXTENTION("파일이 확장자명이 존재하지않습니다", 400),
    INVALID_FILE_EXTENTION("올바르지않은 확장자명입니다", 400),
    IO_EXCEPTION_ON_IMAGE_UPLOAD("IO exception occurred during image upload", 500),
    PUT_OBJECT_EXCEPTION("Error occurred while uploading image to S3", 500),
    IO_EXCEPTION_ON_IMAGE_DELETE("IO exception occurred during image deletion", 500);

    private final String message;
    private final int status;

    ErrorCode(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
