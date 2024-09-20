package com.sysone.ddogdog.customer.reservation.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ReservationDTO {
    private Integer id;
    private Long customerId;
    private Integer hotelId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer count;
    private Integer price;
    private boolean canceled;
    private LocalDate createDate;
}
