package com.sysone.ddogdog.customer.hotel.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseHotelDTO {

    private Integer id;
    private Integer addressId;
    private String ownerId;
    private String businessName;
    private String businessNumber;
    private String phoneNumber;
    private String intro;
    private String mainImage;
    private double avgScore;
    private Integer reviewCount;

    public static ResponseHotelDTO fromHotelDTO(HotelDTO hotel) {
        if(hotel.getReviewCount() == 0) {
            return ResponseHotelDTO.builder()
                .id(hotel.getId())
                .addressId(hotel.getAddressId())
                .ownerId(hotel.getOwnerId())
                .businessName(hotel.getBusinessName())
                .businessNumber(hotel.getBusinessNumber())
                .phoneNumber(hotel.getPhoneNumber())
                .intro(hotel.getIntro())
                .mainImage(hotel.getMainImage())
                .avgScore(0)
                .reviewCount(hotel.getReviewCount())
                .build();
        }
        return ResponseHotelDTO.builder()
            .id(hotel.getId())
            .addressId(hotel.getAddressId())
            .ownerId(hotel.getOwnerId())
            .businessName(hotel.getBusinessName())
            .businessNumber(hotel.getBusinessNumber())
            .phoneNumber(hotel.getPhoneNumber())
            .intro(hotel.getIntro())
            .mainImage(hotel.getMainImage())
            .avgScore((double) Math.round((float) hotel.getTotalScore() / hotel.getReviewCount()*100)/100.0)
            .reviewCount(hotel.getReviewCount())
            .build();
    }
}
