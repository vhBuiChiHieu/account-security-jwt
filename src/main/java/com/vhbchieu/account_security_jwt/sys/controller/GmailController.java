package com.vhbchieu.account_security_jwt.sys.controller;

import com.vhbchieu.account_security_jwt.sys.domain.request.GmailSendRequest;
import com.vhbchieu.account_security_jwt.sys.service.GmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gmail")
@RequiredArgsConstructor
public class GmailController {

    private final GmailService gmailService;

    @PostMapping("send")
    public String sendGmail(@RequestBody GmailSendRequest request) {
        if (gmailService.sendEmail(request)) {
            return "success";
        } else {
            return "fail";
        }
    }
}
