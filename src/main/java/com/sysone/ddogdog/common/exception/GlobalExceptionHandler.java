package com.sysone.ddogdog.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 - 데이터 조회 결과 null일때
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoDataFoundException(NoDataFoundException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoAvailableRoomsException.class)
    public ResponseEntity<ErrorResponse> handleNoAvailableRoomsException(NoAvailableRoomsException ex) {
        return new ResponseEntity<>(new ErrorResponse(CustomerErrorCode.NO_MORE_ROOMS), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NoHandlerFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(CommonErrorCode.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

  //  공통
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(CommonErrorCode.INTERNAL_SERVER_ERROR),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
