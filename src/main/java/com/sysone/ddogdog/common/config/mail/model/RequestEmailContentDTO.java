package com.sysone.ddogdog.common.config.mail.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestEmailContentDTO {
    private String email;
    private String userName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer count;
    private Integer price;
    private LocalDate createDate;
}
