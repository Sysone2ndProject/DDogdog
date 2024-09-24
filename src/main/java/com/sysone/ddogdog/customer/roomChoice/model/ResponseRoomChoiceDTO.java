package com.sysone.ddogdog.customer.roomChoice.model;

import com.sysone.ddogdog.customer.room.model.RoomGrade;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ResponseRoomChoiceDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private RoomGrade grade;
    private String roomImage;
    private String intro;
    private Integer nowPrice;
}
