package com.sysone.ddogdog.customer.reservation.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseReservationDTO {
    private Long id;
    private String mainImage;
    private String intro;
    private String businessName;
    private String fullAddress;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer count;
    private Integer price;
    private LocalDate createDate;
    private boolean canceled;
}
