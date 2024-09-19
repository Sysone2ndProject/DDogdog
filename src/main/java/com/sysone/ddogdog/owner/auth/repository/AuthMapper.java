package com.sysone.ddogdog.owner.auth.repository;

import com.sysone.ddogdog.owner.auth.model.AuthDTO;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {

    void signUp(AuthDTO authDTO);

    //아이디 중복 확인
    Boolean findById(String id);

    Optional<AuthDTO> login(String id);
}