package com.vhbchieu.account_security_jwt.sys.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequest {
    private String token;
}
