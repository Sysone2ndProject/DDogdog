package com.sysone.ddogdog.owner.room.model;

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

    public static RoomDTO fromRequestRoomDTO(RequestRoomDTO requestRoomDTO,String roomImgUrl){
        return RoomDTO.builder()
            .hotelId(requestRoomDTO.getHotelId())
            .grade(requestRoomDTO.getGrade())
            .price(requestRoomDTO.getPrice())
            .maxDogs(requestRoomDTO.getMaxDogs())
            .roomImage(roomImgUrl)
            .intro(requestRoomDTO.getIntro())
            .build();
    }
}