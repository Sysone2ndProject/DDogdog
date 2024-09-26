package com.sysone.ddogdog.customer.reservation.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseReservationStatsDTO {
    private int totalReservations;
    private int pastReservations;
    private int futureReservations;
    private int currentReservations;
}
