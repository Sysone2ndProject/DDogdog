package com.sysone.ddogdog.customer.reservation.service;

import com.sysone.ddogdog.customer.reservation.mapper.ReservationMapper;
import com.sysone.ddogdog.customer.reservation.model.ReservationDTO;
import com.sysone.ddogdog.customer.roomChoice.mapper.RoomChoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationMapper reservationMapper;
//    private final RoomChoiceMapper roomChoiceMapper;

    public void saveReserve(ReservationDTO reservationDTO){
        reservationMapper.saveReserve(reservationDTO);
//        roomChoiceMapper.saveRoomChoice();
    }
}
