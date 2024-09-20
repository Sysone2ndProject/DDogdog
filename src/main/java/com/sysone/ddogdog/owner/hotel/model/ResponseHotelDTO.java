package com.sysone.ddogdog.owner.hotel.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseHotelDTO {

    private Integer hotelId;
    private String businessName;
    private String businessNumber;
    private String phoneNumber;
    private String intro;
    private String mainImage;
    private Float avgScore;
    private Integer reviewCount;
    private String fullAddress;
}