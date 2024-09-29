package com.sysone.ddogdog.customer.reservation.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.reservation.model.ResponseReservationDTO;
import com.sysone.ddogdog.customer.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/v1/customers/reservation")
@RequiredArgsConstructor
public class ReservationViewController {

    private final ReservationService reservationService;
    @GetMapping
    public String findReservation(@AuthenticationPrincipal PrincipalDetails user, @RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "size", defaultValue = "5") int size, Model model){
        Page<ResponseReservationDTO> reservationInfos = reservationService.findReservationsByCustomerId(user.getUsername(),page,size);
        model.addAttribute("ReservationInfos",reservationInfos);
        model.addAttribute("currentPage", page);  // 현재 페이지 번호
        model.addAttribute("totalPages", reservationInfos.getTotalPages());  // 전체 페이지 수
        return "customer/reservationInfo";
    }
}
