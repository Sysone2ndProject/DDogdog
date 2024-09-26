package com.sysone.ddogdog.customer.auth.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestAddressDTO {
    private Long id;
    private String fullAddress;
    private String sido;
    private String sigungu;
    private String dong;
}
