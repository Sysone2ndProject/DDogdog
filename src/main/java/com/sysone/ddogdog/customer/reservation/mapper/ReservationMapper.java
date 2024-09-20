package com.sysone.ddogdog.customer.reservation.mapper;

import com.sysone.ddogdog.customer.reservation.model.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    void saveReserve(ReservationDTO reservationDTO);
}
