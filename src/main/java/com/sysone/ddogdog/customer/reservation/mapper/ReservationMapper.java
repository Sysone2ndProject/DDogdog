package com.sysone.ddogdog.customer.reservation.mapper;

import com.sysone.ddogdog.customer.reservation.model.Reservation;
import com.sysone.ddogdog.customer.reservation.model.ResponseMostReservationHotelDTO;
import com.sysone.ddogdog.customer.reservation.model.ResponseReservationDTO;
import com.sysone.ddogdog.customer.reservation.model.ResponseReservationStatsDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;

@Mapper
public interface ReservationMapper {

    void saveReserve(Reservation reservation);

    List<ResponseReservationDTO> findReservationsByCustomerId(@Param("customerId") Long customerId,  @Param("offset") int offset,
                                                              @Param("limit") int limit);

    void patchReservationCanceled(Long reservationId);

    ResponseReservationStatsDTO findReservationStatsByCustomerId(Long customerId);

    ResponseMostReservationHotelDTO findMostReservationHotelByCustomerId(Long customerId);

    int countReservationsByCustomerId(@Param("customerId")Long customerId);
}
