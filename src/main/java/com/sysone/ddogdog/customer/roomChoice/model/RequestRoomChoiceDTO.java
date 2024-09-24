package com.sysone.ddogdog.customer.roomChoice.model;

import com.sysone.ddogdog.customer.room.model.RoomGrade;
import lombok.Data;

@Data
public class RequestRoomChoiceDTO {
    RoomGrade grade;
    Integer count;
    Integer price;
}
