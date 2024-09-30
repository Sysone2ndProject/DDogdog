package com.sysone.ddogdog.common.config.form;

import com.sysone.ddogdog.owner.auth.model.AuthDTO;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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

    public String getOwnerName() {
        return authDTO.getOwnerName();
    }

    // equals 및 hashCode 재정의 (customerDTO.getId() 기반)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // 자기 자신과의 비교는 항상 true
        if (obj == null || getClass() != obj.getClass()) return false;  // 다른 클래스의 객체와 비교는 false
        CustomDetails that = (CustomDetails) obj;
        return Objects.equals(authDTO.getId(), that.authDTO.getId());  // 유니크한 필드 기반 비교
    }

    @Override
    public int hashCode() {
        return Objects.hash(authDTO.getId());  // 유니크한 필드 기반 hashCode 생성
    }
}