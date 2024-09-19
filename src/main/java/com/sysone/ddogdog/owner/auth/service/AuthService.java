package com.sysone.ddogdog.owner.auth.service;

import com.sysone.ddogdog.common.config.Role;
import com.sysone.ddogdog.owner.auth.model.AuthDTO;
import com.sysone.ddogdog.owner.auth.repository.AuthMapper;
import com.sysone.ddogdog.owner.common.exception.OwnerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMapper authMapper;

    private final PasswordEncoder passwordEncoder;

    public void signUpOwner(AuthDTO authDTO) {
        try {
            authMapper.signUp(AuthDTO.builder()
                .id(authDTO.getId())
                .password(passwordEncoder.encode(authDTO.getPassword()))
                .accountNumber(authDTO.getAccountNumber())
                .bank(authDTO.getBank())
                .ownerName(authDTO.getOwnerName())
                .role(Role.OWNER)
                .build()
            );
        } catch (Exception e) {
            //가입 버튼 클릭 시 에러 확인
            throw new OwnerException("아이디 중복확인 해 주세요", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Boolean findById(String id) {
        return authMapper.findById(id);
    }
}
