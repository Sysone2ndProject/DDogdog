package com.sysone.ddogdog.common.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.auth.service.KakaoService;
import com.sysone.ddogdog.customer.hotel.model.ResponseHotelDTO;
import com.sysone.ddogdog.customer.hotel.service.HotelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class CommonController {

    private final HotelService hotelService;
    private final KakaoService kakaoService;

    @GetMapping
    public String landingMain() {
        return "common/index";
    }

    @GetMapping("/v1/customers")
    public String listHotels(@AuthenticationPrincipal PrincipalDetails user,
        Model model) {
        List<ResponseHotelDTO> hotels = hotelService.getBestHotels();
        model.addAttribute("hotels", hotels);
        if (user != null) {
            Long id = Long.parseLong(user.getUsername());
            List<ResponseHotelDTO> localHotels = hotelService.getBestHotelsById(id);
            String kakaoJsId = kakaoService.getKakaoKey();
            model.addAttribute("localHotels", localHotels);
            model.addAttribute("kakaoId", kakaoJsId);
        }
        return "customer/index";
    }

    @GetMapping("/v1/owners")
    public String ownerMain() {
        return "owner/index";
    }
}