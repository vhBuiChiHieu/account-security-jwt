package com.vhbchieu.account_security_jwt.sys.service;

import com.vhbchieu.account_security_jwt.sys.domain.request.GmailSendRequest;
import org.springframework.stereotype.Service;

@Service
public interface GmailService {
    boolean sendEmail(GmailSendRequest request);
}
