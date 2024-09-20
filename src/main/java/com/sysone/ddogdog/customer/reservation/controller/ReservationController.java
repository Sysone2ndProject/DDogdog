package com.sysone.ddogdog.customer.reservation.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.reservation.model.ReservationDTO;
import com.sysone.ddogdog.customer.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        ReservationDTO reservationDTO){
        log.info("예약 진입");
        System.out.println(reservationDTO.toString());
        reservationService.saveReserve(reservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
