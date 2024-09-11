package com.sysone.ddogdog.owner.auth.model;

import com.sysone.ddogdog.common.config.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDTO {

    private String id;
    private String password;
    private String accountNumber;
    private String bank;
    private String ownerName;
    private Role role;
}