package com.sysone.ddogdog.common.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.hotel.model.ResponseHotelDTO;
import com.sysone.ddogdog.customer.hotel.service.HotelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class CommonController {

    private final HotelService hotelService;

    @Value("${kakao.map.api-key}")
    private String kakaoJsId;

    @GetMapping
    public String landingMain() {
        return "common/index";
    }

    @GetMapping("/customers")
    public String listHotels(@AuthenticationPrincipal PrincipalDetails principalDetails,
        Model model) {
        List<ResponseHotelDTO> hotels = hotelService.getBestHotels();
        model.addAttribute("hotels", hotels);

        if (principalDetails != null) {
            Long id = Long.parseLong(principalDetails.getUsername());

            List<ResponseHotelDTO> localHotels = hotelService.getBestHotelsById(id);
            model.addAttribute("localHotels", localHotels);
            model.addAttribute("kakaoId", kakaoJsId);
        }

        return "customer/index";
    }

    @GetMapping("/owners")
    public String ownerMain() {
        return "owner/index";
    }
}
