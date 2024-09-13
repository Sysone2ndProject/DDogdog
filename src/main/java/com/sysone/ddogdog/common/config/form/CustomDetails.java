package com.sysone.ddogdog.common.config.form;

import com.sysone.ddogdog.owner.auth.model.AuthDTO;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class CustomDetails implements UserDetails {

    private final AuthDTO authDTO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //user의 권한 담기(현재 한 유저당 1개의 권한만 있음)
        return List.of(new SimpleGrantedAuthority("ROLE_" + authDTO.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return authDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return authDTO.getId();
    }
}