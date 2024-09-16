package com.sysone.ddogdog.customer.myPage.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {
    String findFullAddressById(Long addressId);
}
