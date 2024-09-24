package com.sysone.ddogdog.owner.reservation.mapper;

import com.sysone.ddogdog.owner.reservation.model.ResponseReservationDayDTO;
import com.sysone.ddogdog.owner.reservation.model.ResponseReservationMonthDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OwnerReservationMapper {
    List<ResponseReservationMonthDTO> getMonthReservation( @Param("startDate") String startDate, @Param("endDate") String endDate,@Param("hotelId") Integer hotelId);

    List<ResponseReservationDayDTO> getDayReservation(@Param("hotelId") Integer hotelId, @Param("reservationDate") String reservationDate);
}
