package com.sysone.ddogdog.customer.auth.mapper;

import com.sysone.ddogdog.customer.auth.model.AddressDTO;
import com.sysone.ddogdog.customer.auth.model.CustomerDTO;
import com.sysone.ddogdog.customer.auth.model.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KakaoMapper {

    Optional<CustomerDTO> existsById(Long id);

    void saveAddress(AddressDTO addressDTO);

    void saveUserCustomers(User user);

    Long existsAddressIdById(Long id);
}
