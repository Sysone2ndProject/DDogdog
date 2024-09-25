package com.sysone.ddogdog.owner.reservation.controller;

import com.sysone.ddogdog.owner.reservation.model.ResponseReservationDayDTO;
import com.sysone.ddogdog.owner.reservation.model.ResponseReservationMonthDTO;
import com.sysone.ddogdog.owner.reservation.service.OwnerReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("OwnerReservationController")
@RequiredArgsConstructor
@RequestMapping("/v1/owners/reservations")
public class ReservationController {

    private final OwnerReservationService ownerReservationService;

    @GetMapping
    public ResponseEntity<List<ResponseReservationMonthDTO>> getReservations(@RequestParam Integer hotelId,
        @RequestParam Integer year,
        @RequestParam Integer month) {
        System.out.println(year + "년 " + month + "월");
        List<ResponseReservationMonthDTO> reservations = ownerReservationService.getReservation(hotelId, year, month);
        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }

    @GetMapping("/day")
    public ResponseEntity<List<ResponseReservationDayDTO>> getReservationsDay(@RequestParam Integer hotelId,
        @RequestParam Integer year,
        @RequestParam Integer month,
        @RequestParam Integer date) {

        List<ResponseReservationDayDTO> reservations = ownerReservationService.getReservationDay(hotelId, year, month, date);
        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }

}
