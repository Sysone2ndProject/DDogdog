package com.sysone.ddogdog.owner.room.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRoomDTO {

    private Integer hotelId;
    private String business_name;
    private List<RoomDTO> responseRoomDTOList;
}