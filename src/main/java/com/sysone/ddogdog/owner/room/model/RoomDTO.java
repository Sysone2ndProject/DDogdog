package com.sysone.ddogdog.owner.room.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomDTO {

    private RoomGrade grade;
    private Integer hotelId;
    private Integer roomCount;
    private Integer price;
    private Integer maxDogs;
    private String roomImage;
    private String intro;

    public static RoomDTO fromRequestRoomDTO(RequestRoomDTO requestRoomDTO, String roomImgUrl) {
        return RoomDTO.builder()
            .hotelId(requestRoomDTO.getHotelId())
            .grade(requestRoomDTO.getGrade())
            .price(requestRoomDTO.getPrice())
            .maxDogs(requestRoomDTO.getMaxDogs())
            .roomImage(roomImgUrl)
            .intro(requestRoomDTO.getIntro())
            .build();
    }

    public static RoomDTO upDateRoom(RequestRoomDTO requestRoomDTO, String roomImgUrl) {
        return RoomDTO.builder()
            .hotelId(requestRoomDTO.getHotelId())
            .grade(requestRoomDTO.getGrade())
            .roomCount(requestRoomDTO.getRoomCount())
            .price(requestRoomDTO.getPrice())
            .maxDogs(requestRoomDTO.getMaxDogs())
            .roomImage(roomImgUrl)
            .intro(requestRoomDTO.getIntro())
            .build();
    }

    public static RoomDTO updateRoomWithoutImg(RequestRoomDTO requestRoomDTO) {
        return RoomDTO.builder()
            .hotelId(requestRoomDTO.getHotelId())
            .grade(requestRoomDTO.getGrade())
            .roomCount(requestRoomDTO.getRoomCount())
            .price(requestRoomDTO.getPrice())
            .maxDogs(requestRoomDTO.getMaxDogs())
            .intro(requestRoomDTO.getIntro())
            .build();
    }
}