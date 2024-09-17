package com.sysone.ddogdog.customer.auth.model;

import com.sysone.ddogdog.common.config.Role;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;
    private Long addressId;
    private String email;
    private String name;
    private String ageRange;
    private String gender;
    private Role role;

    public static CustomerDTO of(Long id,String email,String name,String ageRange,String gender,Role role){
        return CustomerDTO.builder()
                .id(id)
                .email(email)
                .name(name)
                .ageRange(ageRange)
                .gender(gender)
                .role(role)
                .build();
    }
}
