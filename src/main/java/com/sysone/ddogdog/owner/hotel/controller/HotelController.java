package com.sysone.ddogdog.owner.hotel.controller;

import com.sysone.ddogdog.common.config.form.CustomDetails;
import com.sysone.ddogdog.owner.hotel.model.RequestHotelDTO;
import com.sysone.ddogdog.owner.hotel.service.OwnerHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("ownerHotelController") //customer의 hotellcontroller와  명칭 중복으로 빈등록 이름 지정
@RequiredArgsConstructor
@RequestMapping("/v1/owners/hotels")
public class HotelController {

    private final OwnerHotelService hotelService;

    @PostMapping
    public ResponseEntity<Void> saveHotel(@AuthenticationPrincipal CustomDetails user, @ModelAttribute RequestHotelDTO requestHotelDTO) {
        hotelService.saveHotel(user.getUsername(), requestHotelDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateHotel(@ModelAttribute RequestHotelDTO requestHotelDTO) {
        hotelService.updateHotel(requestHotelDTO);
        return ResponseEntity.ok().build();
    }
}