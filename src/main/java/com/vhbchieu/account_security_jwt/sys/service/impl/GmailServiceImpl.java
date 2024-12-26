package com.vhbchieu.account_security_jwt.sys.service.impl;

import com.vhbchieu.account_security_jwt.sys.domain.request.GmailSendRequest;
import com.vhbchieu.account_security_jwt.sys.service.GmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GmailServiceImpl implements GmailService {

    @Value("${spring.mail.username}")
    private String fromAddress;

    private final JavaMailSender mailSender;

    @Override
    public boolean sendEmail(GmailSendRequest request) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");   //Trợ giúp hiển thị tên

            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(request.getBody());
            //Set name
            helper.setFrom(fromAddress, "HieuOTP");

            mailSender.send(message);
            return true;
        } catch (Exception e) {
            log.error("send email error: {}", e.getMessage(), e);
            return false;
        }
    }
}
