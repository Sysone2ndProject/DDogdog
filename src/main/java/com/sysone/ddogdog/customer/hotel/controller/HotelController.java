package com.sysone.ddogdog.customer.hotel.controller;

import com.sysone.ddogdog.customer.hotel.model.HotelVO;
import com.sysone.ddogdog.customer.hotel.service.HotelService;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/customers/hotels")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public String listHotels(Model model, @RequestParam String keyword,
        @RequestParam Date startDate, @RequestParam Date endDate) {
        List<HotelVO> hotels = hotelService.getHotelsByKeywordAndDates(keyword, startDate, endDate);
        model.addAttribute("hotels", hotels);

        return "customer/hotel";
    }

}
