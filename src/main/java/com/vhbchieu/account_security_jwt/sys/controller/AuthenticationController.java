package com.vhbchieu.account_security_jwt.sys.controller;

import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.dto.LoginDto;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountLoginRequest;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import com.vhbchieu.account_security_jwt.sys.domain.request.LogoutRequest;
import com.vhbchieu.account_security_jwt.sys.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "API Xác thực", description = "Các Api liên quan đến xác thực tài khoản")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Đăng nhập",
            description = "Đăng nhập sử dụng tài khoản và mật khẩu",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Đăng nhập thành công"),
                    @ApiResponse(responseCode = "400", description = "Đăng nhập thất bại"),
            }
    )
    @PostMapping("login")
    public LoginDto login(@RequestBody AccountLoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("register")
    public AccountDto register(@RequestBody AccountRequest accountRequest) {
        return authenticationService.register(accountRequest);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("logout")
    public void logout(@RequestBody LogoutRequest logoutRequest) {
        authenticationService.logout(logoutRequest);
    }
}
