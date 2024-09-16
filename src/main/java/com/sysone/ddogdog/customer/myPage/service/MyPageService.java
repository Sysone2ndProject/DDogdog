package com.sysone.ddogdog.customer.myPage.service;

import com.sysone.ddogdog.customer.myPage.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MyPageMapper myPageMapper;

    public String findFullAddressById(Long addressId){
        return myPageMapper.findFullAddressById(addressId);
    }

}
