package com.sysone.ddogdog.customer.auth.controller;

import com.sysone.ddogdog.customer.auth.model.AddressDTO;
import com.sysone.ddogdog.customer.auth.model.RequestAddressDTO;
import com.sysone.ddogdog.customer.auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class KakaoController {

    private final KakaoService kakaoService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody AddressDTO addressDTO) {
        kakaoService.saveAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/location")
    public ResponseEntity<Void> updateLocation(@RequestBody RequestAddressDTO requestAddressDTO) {
        kakaoService.updateAddress(requestAddressDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}