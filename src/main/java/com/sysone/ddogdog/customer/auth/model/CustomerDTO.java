package com.sysone.ddogdog.customer.auth.model;

import com.sysone.ddogdog.common.config.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;
    private Long addressId;
    private String name;
    private String email;
    private String ageRange;
    private String gender;
    private Role role;
}
