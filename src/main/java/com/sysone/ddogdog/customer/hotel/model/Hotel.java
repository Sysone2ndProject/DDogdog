package com.sysone.ddogdog.customer.hotel.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hotel {

    private Integer id;
    private Integer addressId;
    private String ownerId;
    private String businessName;
    private String businessNumber;
    private String phoneNumber;
    private String intro;
    private String mainImage;
    private Integer totalScore;
    private Integer reviewCount;
}
