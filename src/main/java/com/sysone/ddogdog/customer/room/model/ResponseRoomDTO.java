package com.sysone.ddogdog.customer.room.model;

import lombok.Data;

@Data
public class ResponseRoomDTO {
    private RoomGrade grade;
    private Integer price;
    private Integer maxDogs;
    private String roomImage;
    private String intro;
    private Integer count;
}
