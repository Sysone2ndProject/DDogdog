package com.sysone.ddogdog.customer.hotel.controller;

import com.sysone.ddogdog.customer.auth.service.KakaoService;
import com.sysone.ddogdog.customer.hotel.model.ResponseHotelDTO;
import com.sysone.ddogdog.customer.hotel.model.ResponseHotelDetailsDTO;
import com.sysone.ddogdog.customer.hotel.service.HotelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/customers/hotels")
public class HotelViewController {

    private final HotelService hotelService;
    private final KakaoService kakaoService;

    @GetMapping
    public String hotelList(Model model, @RequestParam String keyword,
        @RequestParam String startDate,
                            @RequestParam String endDate,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "3") int size) {
        Page<ResponseHotelDTO> hotels = hotelService.getHotelsByKeywordAndDatesWithPagination(keyword, startDate,
                endDate,page,size);
        model.addAttribute("hotels", hotels);
        log.info("페이지네이션 결과 반환 -> "+hotels.toString());
        return "customer/hotel";
    }

    @GetMapping("/{id}")
    public String hotelDetails(Model model, @PathVariable Integer id, @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate) {
        ResponseHotelDetailsDTO hotelDetail = hotelService.getHotelDetails(id, startDate, endDate);
        model.addAttribute("hotelDetail", hotelDetail);
        String kakaoJsId = kakaoService.getKakaoKey();
        model.addAttribute("kakaoId", kakaoJsId);

        return "customer/hotelDetail";
    }

}
