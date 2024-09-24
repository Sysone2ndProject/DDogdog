package com.sysone.ddogdog.customer.reservation.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Builder;
import lombok.Data;

/**
 * DB와 직접적으로 통신하는 Entity 객체이다.
 */
@Data
@Builder
public class Reservation {
    private Integer id;
    private Long customerId;
    private Integer hotelId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer count;
    private Integer price;
    private boolean canceled;
    private LocalDate createDate;

    public static Reservation from(RequestReservationDTO dto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return Reservation.builder()
                .customerId(dto.getCustomerId())
                .hotelId(dto.hotelId)
                .startDate(LocalDate.parse(dto.getStartDate(), formatter))
                .endDate(LocalDate.parse(dto.getEndDate(), formatter))
                .count(dto.getCount())
                .price(dto.getPrice())
                .canceled(false)
                .createDate(LocalDate.now())
                .build();
    }
}
