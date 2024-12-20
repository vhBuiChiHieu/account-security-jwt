package com.vhbchieu.account_security_jwt.sys.domain.mapper;

import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.entity.Account;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account getAccount(AccountRequest request) {
        return Account.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(request.getRole())
                .build();
    }

    public AccountDto getDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .email(account.getEmail())
                .phone(account.getPhone())
                .role(account.getRole())
                .updateTime(account.getUpdateTime())
                .build();
    }

    public void requestToAccount(AccountRequest request, Account account) {
        account.setUsername(request.getUsername());
        account.setPassword(request.getPassword());
        account.setEmail(request.getEmail());
        account.setPhone(request.getPhone());
        account.setRole(request.getRole());
    }
}
