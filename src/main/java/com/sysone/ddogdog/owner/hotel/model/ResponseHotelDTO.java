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
    private Integer addressId;
    private String fullAddress;

    public static ResponseHotelDTO fromHotelDTO(HotelDTO hotelDTO, String fullAddress) {
        return ResponseHotelDTO.builder()
            .hotelId(hotelDTO.getId())
            .businessName(hotelDTO.getBusinessName())
            .businessNumber(hotelDTO.getBusinessNumber())
            .phoneNumber(hotelDTO.getPhoneNumber())
            .intro(hotelDTO.getIntro())
            .mainImage(hotelDTO.getMainImage())
            .addressId(hotelDTO.getAddressId())
            .fullAddress(fullAddress)
            .build();
    }
}