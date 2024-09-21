package com.sysone.ddogdog.customer.reservation.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.reservation.model.RequsetReservationDTO;
import com.sysone.ddogdog.customer.reservation.model.Reservation;
import com.sysone.ddogdog.customer.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customers/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> saveReserve(@AuthenticationPrincipal PrincipalDetails user,@RequestBody
    RequsetReservationDTO dto){
        log.info("예약 진입");
        System.out.println(dto.toString());
        reservationService.saveReserve(user.getUsername(),dto);
        //TODO : 페이지가없어 string으로 넘겨주어 테스트 추후 페이지 확인후 삭제예정
//        reservationService.saveReserve("3700680476",dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
