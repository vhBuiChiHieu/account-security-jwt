package com.vhbchieu.account_security_jwt.sys.controller;

import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountDto;
import com.vhbchieu.account_security_jwt.sys.domain.dto.LoginDto;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountLoginRequest;
import com.vhbchieu.account_security_jwt.sys.domain.request.AccountRequest;
import com.vhbchieu.account_security_jwt.sys.domain.request.LogoutRequest;
import com.vhbchieu.account_security_jwt.sys.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
                    @ApiResponse(
                            responseCode = "200",
                            description = "Đăng nhập thành công",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(
                                            value = """
                                                    {
                                                        "token": "eyJhbGciOiJIUzM3Y...."
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Đăng nhập thất bại"),
            }
    )
    @PostMapping("login")
    public LoginDto login(@RequestBody AccountLoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @GetMapping("refresh")
    public LoginDto refresh(HttpServletRequest request) {
        String freshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("fresh_token"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
        return authenticationService.refresh(freshToken);
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
