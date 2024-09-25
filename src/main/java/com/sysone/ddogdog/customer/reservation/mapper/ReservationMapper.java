package com.sysone.ddogdog.customer.reservation.mapper;

import com.sysone.ddogdog.customer.reservation.model.Reservation;
import com.sysone.ddogdog.customer.reservation.model.ResponseReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void saveReserve(Reservation reservation);

    List<ResponseReservationDTO> findReservationsByCustomerId(Long customerId);

    void patchReservationCanceled(Long reservationId);
}
