package com.vhbchieu.account_security_jwt.sys.service;

import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountAuthDto;
import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.entity.Account;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    AccountDto create(AccountRequest request);

    AccountDto getById(Long id);

    AccountAuthDto getAccountAuth(Long id);

    Account getByUsername(String username);

    List<AccountDto> getAll();

    AccountDto update(Long id, AccountRequest accountRequest);

    void deleteById(Long id);
}
