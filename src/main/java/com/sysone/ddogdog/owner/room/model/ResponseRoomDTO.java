package com.sysone.ddogdog.owner.room.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseRoomDTO {
    private RoomGrade grade;
    private Integer roomCount;
    private Integer price;
    private Integer maxDogs;
    private String roomImage;
    private String intro;
}