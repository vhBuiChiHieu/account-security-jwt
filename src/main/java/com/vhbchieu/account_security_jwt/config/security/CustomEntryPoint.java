package com.vhbchieu.account_security_jwt.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vhbchieu.account_security_jwt.common.AppErrorResponse;
import com.vhbchieu.account_security_jwt.config.constant.AccountError;
import com.vhbchieu.account_security_jwt.config.constant.AccountErrorDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        AppErrorResponse errorResponse = new AppErrorResponse(
                request.getRequestId(),
                new AccountErrorDto(AccountError.UNAUTHORIZED)
        );
        //mapping
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.flushBuffer();
    }
}