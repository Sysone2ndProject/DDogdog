package com.sysone.ddogdog.customer.auth.service;


import com.sysone.ddogdog.customer.auth.mapper.KakaoMapper;
import com.sysone.ddogdog.customer.auth.model.AddressDTO;
import com.sysone.ddogdog.customer.auth.model.CustomerDTO;
import java.util.Optional;

import com.sysone.ddogdog.customer.auth.model.RequestAddressDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoService {

    private final KakaoMapper kakaoMapper;

    public Optional<CustomerDTO> findUserById(Long id) {
        return kakaoMapper.findUserById(id);
    }
    @Transactional
    public void saveAddress(AddressDTO AddressDTO) {
        System.out.println("진입테스트");
        kakaoMapper.saveAddress(AddressDTO);
    }

    @Transactional
    public void updateAddress(RequestAddressDTO requestAddressDTO) {
        System.out.println("진입테스트");
        kakaoMapper.updateAddress(requestAddressDTO);
    }
}