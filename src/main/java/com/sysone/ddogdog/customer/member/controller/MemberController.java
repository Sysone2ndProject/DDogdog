package com.sysone.ddogdog.customer.member.controller;

import com.sysone.ddogdog.customer.member.service.MemberService;
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
@RequestMapping("/v1/customers/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/location")
    public ResponseEntity<String> getCustomerAddress(@RequestParam Long addressId) {
        String fullAddress = memberService.findFullAddressById(addressId);
        return ResponseEntity.ok(fullAddress);
    }
}
