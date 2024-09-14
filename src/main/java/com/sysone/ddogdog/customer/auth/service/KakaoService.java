package com.sysone.ddogdog.customer.auth.service;


import com.sysone.ddogdog.customer.auth.mapper.KakaoMapper;
import com.sysone.ddogdog.customer.auth.model.AddressDTO;
import com.sysone.ddogdog.customer.auth.model.CustomerDTO;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoMapper kakaoMapper;

    public Optional<CustomerDTO> checkIfUserExists(Long id) {
        return kakaoMapper.existsById(id);
    }

    public void saveAddress(AddressDTO AddressDTO) {
        System.out.println("진입테스트");
        kakaoMapper.saveAddress(AddressDTO);
    }
}