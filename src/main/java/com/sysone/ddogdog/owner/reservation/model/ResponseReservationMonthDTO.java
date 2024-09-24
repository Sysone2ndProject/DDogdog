package com.sysone.ddogdog.owner.reservation.model;

import com.sysone.ddogdog.owner.room.model.RoomGrade;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseReservationMonthDTO {

    private LocalDate reservationDate;
    private RoomGrade grade;
    private Integer roomCount;
    private Integer price;

}