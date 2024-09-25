package com.sysone.ddogdog.customer.room.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDTO {
    private Integer id;
    private Integer hotelId;
    private RoomGrade grade;
    private Integer price;
    private Integer maxDogs;
    private String roomImage;
    private String intro;
}
