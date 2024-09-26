package com.sysone.ddogdog.customer.reservation.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseMostReservationHotelDTO {
    private Integer hotelId;
    private String businessName;
    private String mainImage;
    private Integer phoneNumber;
    private String intro;
    private String fullAddress;
    private Integer visitCount;
}
