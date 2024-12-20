package com.vhbchieu.account_security_jwt.sys.domain.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountLoginRequest {
    private String username;
    private String password;
}
