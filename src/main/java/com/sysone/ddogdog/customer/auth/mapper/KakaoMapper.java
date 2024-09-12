package com.sysone.ddogdog.customer.auth.mapper;

import com.sysone.ddogdog.customer.auth.model.AddressCustomerDTO;
import com.sysone.ddogdog.customer.auth.model.CustomerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface KakaoMapper {
    Optional<CustomerDTO> existsById(Long id);
    void saveCustomers(AddressCustomerDTO addressCustomerDTO);
}
