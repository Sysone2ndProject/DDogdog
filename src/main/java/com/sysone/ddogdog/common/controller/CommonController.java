package com.sysone.ddogdog.common.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.hotel.model.HotelVO;
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
@RequestMapping("/v1")
public class CommonController {

    private final HotelService hotelService;

    @GetMapping
    public String landingMain() {
        return "common/index";
    }

    @GetMapping("/customers")
    public String listHotels(@AuthenticationPrincipal PrincipalDetails principalDetails,
        Model model) {
        List<HotelVO> hotels = hotelService.getBestHotels();
        model.addAttribute("hotels", hotels);

        Long id = Long.parseLong(principalDetails.getUsername());

        List<HotelVO> localHotels = hotelService.getBestHotelsById(id);
        model.addAttribute("localHotels", localHotels);

        return "customer/index";
    }

    @GetMapping("/owners")
    public String ownerMain() {
        return "owner/index";
    }
}
