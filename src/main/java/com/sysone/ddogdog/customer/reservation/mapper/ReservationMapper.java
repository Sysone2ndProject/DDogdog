package com.sysone.ddogdog.customer.reservation.mapper;

import com.sysone.ddogdog.customer.reservation.model.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    void saveReserve(Reservation reservation);
}
