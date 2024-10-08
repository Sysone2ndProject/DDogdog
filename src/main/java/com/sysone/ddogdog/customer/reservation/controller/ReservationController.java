package com.sysone.ddogdog.customer.reservation.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.reservation.model.RequestReservationDTO;
import com.sysone.ddogdog.customer.reservation.model.ResponseMostReservationHotelDTO;
import com.sysone.ddogdog.customer.reservation.model.ResponseReservationStatsDTO;
import com.sysone.ddogdog.customer.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> saveReserve(@AuthenticationPrincipal PrincipalDetails user,
                                              @RequestBody RequestReservationDTO dto) {
        reservationService.saveReserve(user, dto);
        return new ResponseEntity<>("예약에 성공했습니다", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build(); // 204 No Content 응답
    }

    @GetMapping("/stats")
    public ResponseEntity<ResponseReservationStatsDTO> findReservationStats(@AuthenticationPrincipal PrincipalDetails user) {
        ResponseReservationStatsDTO statsDTO = reservationService.findReservationStatsByCustomerId(user.getUsername());
        return ResponseEntity.ok(statsDTO);
    }

    @GetMapping("/mostHotel")
    public ResponseEntity<ResponseMostReservationHotelDTO> findMostReservationHotelByCustomerId(@AuthenticationPrincipal PrincipalDetails user) {
        ResponseMostReservationHotelDTO mostHotelDTO = reservationService.findMostReservationHotelByCustomerId(user.getUsername());
        return ResponseEntity.ok(mostHotelDTO);
    }
}
