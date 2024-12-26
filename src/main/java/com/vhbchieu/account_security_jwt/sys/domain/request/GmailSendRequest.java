package com.vhbchieu.account_security_jwt.sys.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter()
public class GmailSendRequest {
    private String to;
    private String subject;
    private String body;
}
