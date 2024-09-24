package com.sysone.ddogdog.owner.reservation.model;

import com.sysone.ddogdog.owner.room.model.RoomGrade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationDTO {
    private Long id;
    private Long customerId;
    private String customerName;
    private String startDate;
    private String endDate;
    private String reservationDate;
    private Integer hotelId;
    private RoomGrade grade;
    private Integer price;
    private Integer roomCount;
}