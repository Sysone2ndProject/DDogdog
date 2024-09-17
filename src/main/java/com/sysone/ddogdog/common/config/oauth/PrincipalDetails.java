package com.sysone.ddogdog.common.config.oauth;

import com.sysone.ddogdog.customer.auth.model.CustomerDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@ToString
public class PrincipalDetails implements UserDetails, OAuth2User {
    // 일반 User -> Customer(추가정보)
    private CustomerDTO customerDTO;
    // 카카오 정보
    private OAuth2UserInfo oAuth2UserInfo;

    public PrincipalDetails(CustomerDTO customerDTO, OAuth2UserInfo oAuth2UserInfo) {
        this.customerDTO = customerDTO;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    /**
     * UserDetails 구현
     * 해당 유저의 권한목록 리턴
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_" + customerDTO.getRole().toString();
            }
        });
        return collect;
    }

    /**
     * UserDetails 구현
     * 비밀번호가 없기에 null로 설정해주었다.
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * UserDetails 구현
     * PK값을 반환해준다
     */
    @Override
    public String getUsername() {
        return String.valueOf(customerDTO.getId());
    }

    /**
     * UserDetails 구현
     * 계정 만료 여부
     * true : 만료안됨
     * false : 만료됨
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 비밀번호 만료 여부
     * true : 만료 안됨
     * false : 만료됨
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 활성화 여부
     * true : 활성화됨
     * false : 활성화 안됨
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    /**
     * OAuth2User 구현
     *
     * @return oauth객체의 정보
     */
    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2UserInfo.getAttributes();
    }

    /**
     * OAuth2User 구현
     *
     * @return OAuth2User pk값
     */
    @Override
    public String getName() {
        return oAuth2UserInfo.getName();
    }
}