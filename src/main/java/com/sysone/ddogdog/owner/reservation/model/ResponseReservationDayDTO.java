package com.sysone.ddogdog.owner.reservation.model;

import com.sysone.ddogdog.owner.room.model.RoomGrade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseReservationDayDTO {
    private RoomGrade grade;
    private Integer roomCount;
    private String customerName;
}