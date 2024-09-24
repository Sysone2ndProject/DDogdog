package com.sysone.ddogdog.owner.hotel.controller;

import com.sysone.ddogdog.common.config.form.CustomDetails;
import com.sysone.ddogdog.owner.hotel.model.ResponseHotelDTO;
import com.sysone.ddogdog.owner.hotel.service.OwnerHotelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("ownerHotelViewController")
@RequiredArgsConstructor
@RequestMapping("/v1/owners/hotels")
public class HotelViewController {

    private final OwnerHotelService hotelService;

    @GetMapping("/form")
    public String getHotelForm() {
        return "owner/hotelRegister";
    }

    @GetMapping
    public String getHotelList(@AuthenticationPrincipal CustomDetails user, Model model) {

        List<ResponseHotelDTO> hotels = hotelService.getHotelsByUserId(user.getUsername());
        model.addAttribute("hotels", hotels);
        return "owner/hotelList";
    }

    @GetMapping("/{id}")
    public String getHotel(@PathVariable Integer id, Model model) {
        ResponseHotelDTO hotel = hotelService.getHotel(id);
        model.addAttribute("hotel", hotel);
        return "owner/updateHotel";
    }

}