package com.vhbchieu.account_security_jwt.sys.controller;

import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountAuthDto;
import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import com.vhbchieu.account_security_jwt.sys.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    //Create account
    @PostMapping
    AccountDto createAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.create(accountRequest);
    }

    //Get an Account
    @GetMapping("/{id}")
    AccountDto getAccount(@PathVariable Long id) {
        return accountService.getById(id);
    }

    //Get all
    @GetMapping()
    List<AccountDto> getAllAccounts() {
        return accountService.getAll();
    }

    //Update
    @PutMapping("/{id}")
    AccountDto updateAccount(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        return accountService.update(id, accountRequest);
    }

    //Delete
    @DeleteMapping("/{id}")
    void deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
    }

    //Get info
    @GetMapping("info")
    AccountDto getAccountInfo() {
        //Lấy người dùng hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountAuthDto accountAuthDto = (AccountAuthDto) authentication.getPrincipal();
        //
        return accountService.getAccountInfo(accountAuthDto.getId());
    }
}
