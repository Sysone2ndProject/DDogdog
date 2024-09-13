package com.sysone.ddogdog.common.config.form;

import com.sysone.ddogdog.owner.auth.model.AuthDTO;
import com.sysone.ddogdog.owner.auth.repository.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomDetailService implements UserDetailsService {

    private final AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        AuthDTO authDTO = authMapper.login(id)
            .orElseThrow(() -> new UsernameNotFoundException(id + "은 없는 아이디입니다."));
        return new CustomDetails(authDTO);
    }
}