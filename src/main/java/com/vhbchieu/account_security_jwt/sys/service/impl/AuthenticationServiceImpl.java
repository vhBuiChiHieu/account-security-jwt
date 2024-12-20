package com.vhbchieu.account_security_jwt.sys.service.impl;

import com.vhbchieu.account_security_jwt.config.constant.AccountError;
import com.vhbchieu.account_security_jwt.config.security.JWTTokenProvider;
import com.vhbchieu.account_security_jwt.exception.AccountException;
import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.dto.LoginDto;
import com.vhbchieu.account_security_jwt.sys.domain.entity.Account;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountLoginRequest;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import com.vhbchieu.account_security_jwt.sys.service.AccountService;
import com.vhbchieu.account_security_jwt.sys.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountService accountService;
    private final JWTTokenProvider jwtTokenProvider;

    @Override
    public LoginDto login(AccountLoginRequest request) {
        Account account = accountService.getByUsername(request.getUsername());
        //check user
        if (account == null) {
            throw new AccountException(AccountError.ACCOUNT_NOT_FOUND);
        }
        //check password
        if (!new BCryptPasswordEncoder().matches(request.getPassword(), account.getPassword())) {
            throw new AccountException(AccountError.PASSWORD_INCORRECT);
        }
        //pass test
        String token = jwtTokenProvider.generateToken(account.getId(), account.getRole().name());
        return new LoginDto(token);
    }

    @Override
    public AccountDto register(AccountRequest request) {
        return accountService.create(request);
    }
}
