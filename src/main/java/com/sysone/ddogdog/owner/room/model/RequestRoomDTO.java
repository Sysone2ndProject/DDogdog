package com.sysone.ddogdog.owner.room.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class RequestRoomDTO {

    private Integer id;
    private Integer hotelId;
    private RoomGrade grade;
    private Integer roomCount;
    private Integer price;
    private Integer maxDogs;
    private MultipartFile roomImage;
    private String intro;
}