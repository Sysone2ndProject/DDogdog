package com.sysone.ddogdog.customer.reservation.service;

import com.sysone.ddogdog.common.config.mail.model.RequestEmailContentDTO;
import com.sysone.ddogdog.common.config.mail.service.EmailService;
import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationMapper reservationMapper;
    private final RoomChoiceService roomChoiceService;
    private final EmailService emailService;

    /**
     * 예약 정보 저장 - 예약 저장 후 해당 pk를 받아, 방선택도 저장
     * @param user 서버 인증 객체로부터 인증 정보
     * @param dto 프론트로부터 받아오는 요청 객체
     */
    @Transactional
    public void saveReserve(PrincipalDetails user, RequestReservationDTO dto){
        dto.setCustomerId(Long.parseLong(user.getUsername()));
        Reservation reservation = Reservation.from(dto);
        reservationMapper.saveReserve(reservation);
        roomChoiceService.saveRoomChoice(reservation.getId(),reservation.getStartDate(),reservation.getEndDate(),dto.getHotelId(),dto.getRooms());
        RequestEmailContentDTO emailDTO =
            RequestEmailContentDTO.builder()
            .email(user.getCustomerDTO().getEmail())
            .userName(user.getName())
            .startDate(reservation.getStartDate())
            .endDate(reservation.getEndDate())
            .count(reservation.getCount())
            .price(reservation.getPrice())
            .createDate(reservation.getCreateDate()).build();
        emailService.sendEmailNotice(emailDTO);
    }

    /**
     * 예약 정보 조회
     * @param customerId
     */
    public Page<ResponseReservationDTO> findReservationsByCustomerId(String customerId, int page, int size){
        int offset = (page - 1) * size;  // offset 계산

        // 페이지네이션된 예약 정보 가져오기 (필요한 데이터만 DB에서 조회)
        List<ResponseReservationDTO> reservations = reservationMapper.findReservationsByCustomerId(Long.parseLong(customerId), offset, size);

        // 총 예약 수 조회 (전체 개수를 가져오는 별도 쿼리)
        int totalReservations = reservationMapper.countReservationsByCustomerId(Long.parseLong(customerId));

        // Page 객체로 반환 (데이터 목록, 페이지 정보, 총 레코드 수)
        return new PageImpl<>(reservations, PageRequest.of(page - 1, size), totalReservations);
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
