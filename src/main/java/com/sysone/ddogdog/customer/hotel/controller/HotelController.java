package com.sysone.ddogdog.customer.hotel.controller;

import com.sysone.ddogdog.customer.hotel.model.ResponseHotelDTO;
import com.sysone.ddogdog.customer.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/v1/customers/getDataList")
    public ResponseEntity<List<String>> getDataList(@RequestParam String searchKeyword) {
        List<String> dataList = hotelService.getDataList(searchKeyword);
        return ResponseEntity.ok(dataList);
    }

    @GetMapping("/v1/customers/hotels/more")
    public ResponseEntity<Page<ResponseHotelDTO>> hotelList(Model model, @RequestParam String keyword,
                                                            @RequestParam String startDate, @RequestParam String endDate,
                                                            @RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "3") int size) {
        Page<ResponseHotelDTO> hotels = hotelService.getHotelsByKeywordAndDatesWithPagination(keyword, startDate,
                endDate, page, size);
        return ResponseEntity.ok(hotels);
    }
}
