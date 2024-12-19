package com.vhbchieu.account_security_jwt.sys.domain.mapper;

import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.entity.Account;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AccountMapper {

    public Account toAccount(AccountRequest request) {
        return Account.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(request.getRole())
                .build();
    }

    public AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .email(account.getEmail())
                .phone(account.getPhone())
                .role(account.getRole())
                .updateTime(account.getUpdateTime())
                .build();
    }
}
