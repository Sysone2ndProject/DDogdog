package com.sysone.ddogdog.customer.hotel.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelVO {

    private Integer id;
    private Integer addressId;
    private String ownerId;
    private String businessName;
    private String businessNumber;
    private String phoneNumber;
    private String intro;
    private String mainImage;
    private float avgScore;
    private Integer reviewCount;

    public static HotelVO fromHotelDTO(Hotel hotel) {
        return HotelVO.builder()
            .id(hotel.getId())
            .addressId(hotel.getAddressId())
            .ownerId(hotel.getOwnerId())
            .businessName(hotel.getBusinessName())
            .businessNumber(hotel.getBusinessNumber())
            .phoneNumber(hotel.getPhoneNumber())
            .intro(hotel.getIntro())
            .mainImage(hotel.getMainImage())
            .avgScore((float) hotel.getTotalScore() / hotel.getReviewCount())
            .reviewCount(hotel.getReviewCount())
            .build();
    }
}
