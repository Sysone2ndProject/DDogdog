package com.sysone.ddogdog.owner.room.model;

import java.util.List;
import lombok.Getter;

@Getter
public class RoomVO {

    private Integer hotelId;
    private String business_name;
    private List<ResponseRoomDTO> responseRoomDTOList;

    private RoomVO() {

    }

    public RoomVO(Integer hotelId, String business_name, List<ResponseRoomDTO> responseRoomDTOList) {
        this.hotelId = hotelId;
        this.business_name = business_name;
        this.responseRoomDTOList = responseRoomDTOList;
    }
}
