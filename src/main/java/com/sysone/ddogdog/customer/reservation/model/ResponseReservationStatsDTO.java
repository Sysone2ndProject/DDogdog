package com.sysone.ddogdog.customer.reservation.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseReservationStatsDTO {
    private Integer totalReservations;
    private Integer pastReservations;
    private Integer futureReservations;
    private Integer currentReservations;
}
