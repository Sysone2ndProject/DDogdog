package com.sysone.ddogdog.customer.myPage.controller;

import com.sysone.ddogdog.customer.myPage.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/location")
    public ResponseEntity<?> getCustomerAddress(@RequestParam Long addressId) {
        String fullAddress = myPageService.findFullAddressById(addressId);
        log.info(fullAddress);
        return ResponseEntity.ok(fullAddress);
    }
}
