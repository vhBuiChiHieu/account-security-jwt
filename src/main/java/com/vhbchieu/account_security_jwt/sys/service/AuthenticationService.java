package com.vhbchieu.account_security_jwt.sys.service;

import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.dto.LoginDto;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountLoginRequest;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    LoginDto login(AccountLoginRequest request);
    AccountDto register (AccountRequest request);
}