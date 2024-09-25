package com.sysone.ddogdog.owner.reservation.controller;

import com.sysone.ddogdog.common.config.form.CustomDetails;
import com.sysone.ddogdog.owner.hotel.model.ResponseHotelDTO;
import com.sysone.ddogdog.owner.hotel.service.OwnerHotelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller("OwnerReservationViewController")
@RequiredArgsConstructor
@RequestMapping("/v1/owners/reservations")
public class ReservationViewController {

    private final OwnerHotelService ownerHotelService;

    @GetMapping("/form")
    public String getForm(@AuthenticationPrincipal CustomDetails user, Model model) {
        List<ResponseHotelDTO> hotels = ownerHotelService.getHotelsByUserId(user.getUsername());
        model.addAttribute("hotels", hotels);
        return "owner/reservation";
    }
}