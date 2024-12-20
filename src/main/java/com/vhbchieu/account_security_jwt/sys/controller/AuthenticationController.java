package com.vhbchieu.account_security_jwt.sys.controller;

import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.dto.LoginDto;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountLoginRequest;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import com.vhbchieu.account_security_jwt.sys.domain.request.LogoutRequest;
import com.vhbchieu.account_security_jwt.sys.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("login")
    public LoginDto login(@RequestBody AccountLoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("register")
    public AccountDto register(@RequestBody AccountRequest accountRequest) {
        return authenticationService.register(accountRequest);
    }

    @PostMapping("logout")
    public void logout(@RequestBody LogoutRequest logoutRequest) {
        authenticationService.logout(logoutRequest);
    }
}
