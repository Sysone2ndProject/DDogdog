package com.sysone.ddogdog.customer.roomChoice.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class RoomChoice {
    private Integer id;
    private Integer reservationId;
    private Integer roomId;
    private Integer nowPrice;

    public static RoomChoice from(Integer reservationId,Integer roomId,Integer nowPrice){
        return RoomChoice.builder()
                .reservationId(reservationId)
                .roomId(roomId)
                .nowPrice(nowPrice)
                .build();
    }
}
