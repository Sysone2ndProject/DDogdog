package com.sysone.ddogdog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomerErrorCode implements ErrorCode{
    NO_DATA_FOUND(HttpStatus.NOT_FOUND, "해당 데이터를 찾을 수 없습니다."),
    NO_DATA_MOST_HOTEL(HttpStatus.NOT_FOUND, "제일 많이 방문한 호텔 데이터가 없습니다."),
    NO_DATA_PETS(HttpStatus.NOT_FOUND, "등록된 펫 정보가 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
