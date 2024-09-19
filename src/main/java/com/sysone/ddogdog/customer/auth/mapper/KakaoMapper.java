package com.sysone.ddogdog.customer.auth.mapper;

import com.sysone.ddogdog.customer.auth.model.AddressDTO;
import com.sysone.ddogdog.customer.auth.model.CustomerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface KakaoMapper {

    Optional<CustomerDTO> findUserById(Long id);

    void saveAddress(AddressDTO addressDTO);

    void saveUserCustomers(CustomerDTO customerDTO);

    Long existsAddressIdById(Long id);
}
