package com.sysone.ddogdog.customer.reservation.service;

import com.sysone.ddogdog.customer.reservation.mapper.ReservationMapper;
import com.sysone.ddogdog.customer.reservation.model.RequsetReservationDTO;
import com.sysone.ddogdog.customer.reservation.model.Reservation;
import com.sysone.ddogdog.customer.roomChoice.service.RoomChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationMapper reservationMapper;
    private final RoomChoiceService roomChoiceService;

    /**
     * 예약 정보 저장 - 예약 저장 후 해당 pk를 받아, 방선택도 저장
     * @param customerId 서버 인증 객체로부터 받아오는 customer pk
     * @param dto 프론트로부터 받아오는 요청 객체
     */
    @Transactional
    public void saveReserve(String customerId, RequsetReservationDTO dto){
        dto.setCustomerid(Long.parseLong(customerId));
        Reservation reservation = Reservation.from(dto);
        reservationMapper.saveReserve(reservation);
        roomChoiceService.saveRoomChoice(reservation.getId(),reservation.getStartDate(),reservation.getEndDate(),dto.getHotelId(),dto.getRooms());
    }
}
