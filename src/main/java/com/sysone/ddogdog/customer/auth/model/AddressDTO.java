package com.sysone.ddogdog.customer.auth.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    private String fullAddress;
    private String sido;
    private String sigungu;
    private String bname;
    private Long kakaoId;
}
