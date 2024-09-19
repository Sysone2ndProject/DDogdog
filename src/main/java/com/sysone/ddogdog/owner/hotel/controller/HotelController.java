package com.sysone.ddogdog.owner.hotel.controller;

import com.sysone.ddogdog.common.config.form.CustomDetails;
import com.sysone.ddogdog.owner.hotel.model.RequestHotelDTO;
import com.sysone.ddogdog.owner.hotel.service.OwnerHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("ownerHotelController") //customer의 hotellcontroller와  명칭 중복으로 빈등록 이름 지정
@RequiredArgsConstructor
@RequestMapping("/v1/owners/hotel")
public class HotelController {

    private final OwnerHotelService hotelService;

    @GetMapping
    public String getHotelForm() {
        return "owner/hotelRegister";
    }

    @PostMapping
    public ResponseEntity<?> saveHotel(@AuthenticationPrincipal CustomDetails user, @ModelAttribute RequestHotelDTO requestHotelDTO) {
        hotelService.saveHotel(user.getUsername(), requestHotelDTO);
        return ResponseEntity.ok().build();
    }
}