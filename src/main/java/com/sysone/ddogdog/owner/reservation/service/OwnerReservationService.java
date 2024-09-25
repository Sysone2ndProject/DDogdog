package com.sysone.ddogdog.owner.reservation.service;

import com.sysone.ddogdog.owner.reservation.mapper.OwnerReservationMapper;
import com.sysone.ddogdog.owner.reservation.model.ResponseReservationDayDTO;
import com.sysone.ddogdog.owner.reservation.model.ResponseReservationMonthDTO;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerReservationService {

    private final OwnerReservationMapper ownerReservationMapper;

    public List<ResponseReservationMonthDTO> getReservation(Integer hotelId, Integer year, Integer month) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate;
        String endDate;
        if(month != 12){
            startDate = YearMonth.of(year, month).atDay(1).format(formatter);
            //요청월의 다음달
            endDate = YearMonth.of(year, month + 1).atDay(1).format(formatter);
        }else{
            startDate = YearMonth.of(year, month).atDay(1).format(formatter);
            //요청월의 다음달
            endDate = YearMonth.of(year + 1, 1).atDay(1).format(formatter);
        }
        return ownerReservationMapper.getMonthReservation(startDate, endDate, hotelId);

    }

    public List<ResponseReservationDayDTO> getReservationDay(Integer hotelId, Integer year, Integer month, Integer date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate reservationDate = LocalDate.of(year, month, date);
        String reservationDateStr = reservationDate.format(formatter);
        return ownerReservationMapper.getDayReservation(hotelId, reservationDateStr);
    }

}
