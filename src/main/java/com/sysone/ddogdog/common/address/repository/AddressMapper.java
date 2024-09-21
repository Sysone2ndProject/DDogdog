package com.sysone.ddogdog.common.address.repository;

import com.sysone.ddogdog.common.address.model.AddressDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {

    void saveAddress(AddressDTO addressDTO);

    String getFullAddressById(Integer id);
}