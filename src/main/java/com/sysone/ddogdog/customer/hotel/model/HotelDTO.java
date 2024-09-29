package com.sysone.ddogdog.customer.hotel.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class HotelDTO {

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
    private Integer price;
}
