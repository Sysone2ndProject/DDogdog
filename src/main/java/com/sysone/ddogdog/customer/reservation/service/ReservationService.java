package com.sysone.ddogdog.customer.reservation.service;

import com.sysone.ddogdog.common.exception.CustomerErrorCode;
import com.sysone.ddogdog.common.exception.NoDataFoundException;
import com.sysone.ddogdog.customer.reservation.mapper.ReservationMapper;
import com.sysone.ddogdog.customer.reservation.model.RequestReservationDTO;
import com.sysone.ddogdog.customer.reservation.model.Reservation;
import com.sysone.ddogdog.customer.reservation.model.ResponseMostReservationHotelDTO;
import com.sysone.ddogdog.customer.reservation.model.ResponseReservationDTO;
import com.sysone.ddogdog.customer.reservation.model.ResponseReservationStatsDTO;
import com.sysone.ddogdog.customer.roomChoice.service.RoomChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationMapper reservationMapper;
    private final RoomChoiceService roomChoiceService;

    /**
     * 예약 정보 저장 - 예약 저장 후 해당 pk를 받아, 방선택도 저장
     * @param customerId 서버 인증 객체로부터 받아오는 customer pk
     * @param dto 프론트로부터 받아오는 요청 객체
     */
    @Transactional
    public void saveReserve(String customerId, RequestReservationDTO dto){
        dto.setCustomerId(Long.parseLong(customerId));
        Reservation reservation = Reservation.from(dto);
        reservationMapper.saveReserve(reservation);
        roomChoiceService.saveRoomChoice(reservation.getId(),reservation.getStartDate(),reservation.getEndDate(),dto.getHotelId(),dto.getRooms());
    }

    /**
     * 예약 정보 조회
     * @param customerId
     */
    public List<ResponseReservationDTO> findReservationsByCustomerId(String customerId){
        return reservationMapper.findReservationsByCustomerId(Long.parseLong(customerId));
    }

    /**
     * 예약 취소
     * @param  reservationId
     */
    @Transactional
    public void cancelReservation(Long reservationId){
        reservationMapper.patchReservationCanceled(reservationId);
    }

    /**
     * 예약 stats 조회
     * @param  customerId
     */
    public ResponseReservationStatsDTO findReservationStatsByCustomerId(String customerId){
        return reservationMapper.findReservationStatsByCustomerId(Long.parseLong(customerId));
    }

    /**
     * 가장 자주 사용한 호텔 정보 조회
     * @param customerId
     */
    public ResponseMostReservationHotelDTO findMostReservationHotelByCustomerId(String customerId){
        ResponseMostReservationHotelDTO result= reservationMapper.findMostReservationHotelByCustomerId(Long.parseLong(customerId));
        if(result==null){
            throw new NoDataFoundException(CustomerErrorCode.NO_DATA_MOST_HOTEL);
        }
        return result;
    }
}
