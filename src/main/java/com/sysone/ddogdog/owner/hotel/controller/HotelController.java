package com.sysone.ddogdog.owner.hotel.controller;

import com.sysone.ddogdog.common.config.form.CustomDetails;
import com.sysone.ddogdog.owner.hotel.model.RequestHotelDTO;
import com.sysone.ddogdog.owner.hotel.model.ResponseHotelDTO;
import com.sysone.ddogdog.owner.hotel.service.OwnerHotelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("ownerHotelController") //customer의 hotellcontroller와  명칭 중복으로 빈등록 이름 지정
@RequiredArgsConstructor
@RequestMapping("/v1/owners/hotels")
public class HotelController {

    private final OwnerHotelService hotelService;

    @GetMapping("/form")
    public String getHotelForm() {
        return "owner/hotelRegister";
    }

    @PostMapping
    public ResponseEntity<Void> saveHotel(@AuthenticationPrincipal CustomDetails user, @ModelAttribute RequestHotelDTO requestHotelDTO) {
        hotelService.saveHotel(user.getUsername(), requestHotelDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public String getHotelList(@AuthenticationPrincipal CustomDetails user, Model model) {

        List<ResponseHotelDTO> hotels = hotelService.getHotelsByUserId(user.getUsername());
        model.addAttribute("hotels", hotels);
        return "owner/hotelList";
    }

}