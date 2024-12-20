package com.vhbchieu.account_security_jwt.sys.service.impl;

import com.vhbchieu.account_security_jwt.config.constant.AccountError;
import com.vhbchieu.account_security_jwt.exception.AccountException;
import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountAuthDto;
import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.entity.Account;
import com.vhbchieu.account_security_jwt.sys.domain.mapper.AccountMapper;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import com.vhbchieu.account_security_jwt.sys.repos.AccountRepository;
import com.vhbchieu.account_security_jwt.sys.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto create(AccountRequest request) {
        if (getByUsername(request.getUsername()) != null)
            throw new AccountException(AccountError.USERNAME_ALREADY_EXISTS);
        Account newAccount = accountMapper.getAccount(request);
        newAccount.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        newAccount.setUpdateTime(new Date());
        accountRepository.save(newAccount);
        return accountMapper.getDto(newAccount);
    }

    @Override
    public AccountDto getById(Long id) {
        return accountMapper.getDto(accountRepository.getAccountById(id));
    }

    @Override
    public Account getByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public List<AccountDto> getAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(accountMapper::getDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto update(Long id, AccountRequest accountRequest) {
        if (!accountRepository.existsById(id))
            throw new AccountException(AccountError.USER_NOT_EXIST);

        Account existingAccount = accountRepository.findAccountById(id);
        accountMapper.requestToAccount(accountRequest, existingAccount);
        existingAccount.setUpdateTime(new Date());
        //update
        accountRepository.save(existingAccount);
        return accountMapper.getDto(existingAccount);
    }

    @Override
    public AccountAuthDto getAccountAuth(Long id) {
        return new AccountAuthDto(accountRepository.getAccountById(id));
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto getAccountInfo(Long id) {
        return accountMapper.getDto(accountRepository.getAccountById(id));
    }
}
