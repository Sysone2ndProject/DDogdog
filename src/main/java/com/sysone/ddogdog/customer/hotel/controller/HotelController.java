package com.sysone.ddogdog.customer.hotel.controller;

import com.sysone.ddogdog.customer.hotel.service.HotelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/v1/customers/getDataList")
    public ResponseEntity<List<String>> getDataList(@RequestParam String searchKeyword) {
        List<String> dataList= hotelService.getDataList(searchKeyword);
        return ResponseEntity.ok(dataList);
    }
}
