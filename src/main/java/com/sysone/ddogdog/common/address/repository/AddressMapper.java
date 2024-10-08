package com.sysone.ddogdog.common.address.repository;

import com.sysone.ddogdog.common.address.model.AddressDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {

    void saveAddress(AddressDTO addressDTO);


    AddressDTO getAddressById(Integer id);


    String getFullAddressById(Integer id);

    void updateAddress(AddressDTO addressDTO);
}

