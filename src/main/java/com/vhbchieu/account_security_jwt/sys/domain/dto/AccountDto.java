package com.vhbchieu.account_security_jwt.sys.domain.dto;

import com.vhbchieu.account_security_jwt.config.constant.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class AccountDto {

    private Long id;
    private String username;
    private String email;
    private String phone;

    private Role role;

    private Date updateTime;
}
