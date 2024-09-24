package com.sysone.ddogdog.owner.reservation.controller;

import com.sysone.ddogdog.owner.reservation.model.ResponseReservationDayDTO;
import com.sysone.ddogdog.owner.reservation.model.ResponseReservationMonthDTO;
import com.sysone.ddogdog.owner.reservation.service.OwnerReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller("OwnerReservationController")
@RequiredArgsConstructor
@RequestMapping("/v1/owners/reservations")
public class ReservationViewController {

    private final OwnerReservationService ownerReservationService;

    @GetMapping
    public String getReservations(@RequestParam Integer hotelId,
        @RequestParam Integer year,
        @RequestParam Integer month, Model model) {
        List<ResponseReservationMonthDTO> reservations = ownerReservationService.getReservation(hotelId, year, month);
        model.addAttribute("hotelId", hotelId);
        model.addAttribute("reservations", reservations);
        return "owner/monthReservation";
    }

    @GetMapping("/day")
    public String getReservationsDay(@RequestParam Integer hotelId,
        @RequestParam Integer year,
        @RequestParam Integer month,
        @RequestParam Integer day, Model model) {

        List<ResponseReservationDayDTO> reservations = ownerReservationService.getReservationDay(hotelId, year, month, day);
        model.addAttribute("reservations", reservations);
        return "owner/dayReservation";
    }

    @GetMapping("/form")
    public String getForm(){
        return "owner/calendar";
    }
}