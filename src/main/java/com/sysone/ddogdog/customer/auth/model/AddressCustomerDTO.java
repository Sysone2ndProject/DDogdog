package com.sysone.ddogdog.customer.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class AddressCustomerDTO {
    private Long id;
    private String fullAddress;
    private String sido;
    private String sigungu;
    private String detailAddress;
    private Long kakaoId;
    private String email;
    private String name;
    private String ageRange;
    private String gender;
}
