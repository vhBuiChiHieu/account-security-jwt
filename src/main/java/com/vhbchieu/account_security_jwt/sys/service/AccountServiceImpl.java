package com.vhbchieu.account_security_jwt.sys.service;

import com.vhbchieu.account_security_jwt.config.constant.AccountError;
import com.vhbchieu.account_security_jwt.exception.AccountException;
import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.entity.Account;
import com.vhbchieu.account_security_jwt.sys.domain.mapper.AccountMapper;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import com.vhbchieu.account_security_jwt.sys.repos.AccountRepository;
import lombok.RequiredArgsConstructor;
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
        Account newAccount = accountMapper.toAccount(request);
        newAccount.setUpdateTime(new Date());
        accountRepository.save(newAccount);
        return accountMapper.toDto(newAccount);
    }

    @Override
    public AccountDto getById(Long id) {
        return accountMapper.toDto(accountRepository.getAccountById(id));
    }

    @Override
    public Account getByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public List<AccountDto> getAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(accountMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto update(Long id, AccountRequest accountRequest) {
        if (!accountRepository.existsById(id))
            throw new AccountException(AccountError.USER_NOT_EXIST);
        if (getByUsername(accountRequest.getUsername()) != null)
            throw new AccountException(AccountError.USERNAME_ALREADY_EXISTS);
        Account existedAcc = accountMapper.toAccount(accountRequest);
        existedAcc.setId(id);
        existedAcc.setUpdateTime(new Date());
        accountRepository.save(existedAcc);
        return accountMapper.toDto(existedAcc);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
