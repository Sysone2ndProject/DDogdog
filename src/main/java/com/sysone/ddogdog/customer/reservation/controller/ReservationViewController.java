package com.sysone.ddogdog.customer.reservation.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.reservation.model.ResponseReservationDTO;
import com.sysone.ddogdog.customer.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1/customers/reservation")
@RequiredArgsConstructor
public class ReservationViewController {

    private final ReservationService reservationService;
    @GetMapping
    public String findReservation(@AuthenticationPrincipal PrincipalDetails user, Model model){
        List<ResponseReservationDTO> reservationInfos = reservationService.findReservationsByCustomerId(user.getUsername());
        for(ResponseReservationDTO responseReservationDTO : reservationInfos){
            System.out.println(responseReservationDTO.toString());
        }
        model.addAttribute("ReservationInfos",reservationInfos);
        return "customer/reservationInfo";
    }
}
