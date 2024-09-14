package com.sysone.ddogdog.customer.auth.model;

import com.sysone.ddogdog.common.config.Role;
import lombok.Builder;
import lombok.Getter;
// TODO : 추후 USER -> USERDTO 로 네이밍 변경 예정
@Getter
@Builder
public class User {
    private Long id;
    private Long addressId;
    private String email;
    private String nickname;
    private String ageRange;
    private String gender;
    private Role role;

    public static User of(Long id,String email,String nickname,String ageRange,String gender,Role role){
        return User.builder()
            .id(id)
            .email(email)
            .nickname(nickname)
            .ageRange(ageRange)
            .gender(gender)
            .role(role)
            .build();
    }
}
