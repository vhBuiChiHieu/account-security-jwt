package com.vhbchieu.account_security_jwt.config.security;

import com.vhbchieu.account_security_jwt.sys.domain.dto.AccountAuthDto;
import com.vhbchieu.account_security_jwt.sys.service.AccountService;
import com.vhbchieu.account_security_jwt.sys.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTTokenProvider jwtTokenProvider;
    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String jwtId;
        final Long userId;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);    //next filter
            return;
        }
        //jwt
        token = authHeader.substring(7);

        //Chặn các token còn hạn những đã logout
        jwtId = jwtTokenProvider.getJwtId(token);
        if (authenticationService.isLogout(jwtId)){
            filterChain.doFilter(request, response);
            return;
        }

        //Phân tích các token hợp lệ
        userId = jwtTokenProvider.getUserId(token);
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            AccountAuthDto accountAuth = accountService.getAccountAuth(userId);
            //create Auth
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    accountAuth,
                    null,
                    accountAuth.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
