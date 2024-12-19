package com.vhbchieu.account_security_jwt.sys.domain.request;

import com.vhbchieu.account_security_jwt.config.constant.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountRequest {

    private String username;
    private String password;
    private String email;
    private String phone;

    private Role role;
}
