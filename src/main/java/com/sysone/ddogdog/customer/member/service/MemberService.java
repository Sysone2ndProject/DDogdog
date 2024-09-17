package com.sysone.ddogdog.customer.member.service;

import com.sysone.ddogdog.customer.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public String findFullAddressById(Long addressId){
        return memberMapper.findFullAddressById(addressId);
    }

}
