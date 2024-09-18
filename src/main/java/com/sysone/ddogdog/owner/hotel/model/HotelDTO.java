package com.sysone.ddogdog.owner.hotel.model;

import lombok.Builder;
import lombok.Data;

@Data
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

    public static HotelDTO saveFromHotelDTO(RequestHotelDTO requestHotelDTO, Integer addressId, String mainImgUrl) {
        return HotelDTO.builder()
            .addressId(addressId)
            .ownerId(requestHotelDTO.getOwnerId())
            .businessName(requestHotelDTO.getBusinessName())
            .businessNumber(requestHotelDTO.getBusinessNumber())
            .phoneNumber(requestHotelDTO.getPhoneNumber())
            .intro(requestHotelDTO.getIntro())
            .mainImage(mainImgUrl)
            .totalScore(0)
            .reviewCount(0)
            .build();
    }
}