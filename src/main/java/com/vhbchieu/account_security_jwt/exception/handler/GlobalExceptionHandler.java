package com.vhbchieu.account_security_jwt.exception.handler;

import com.vhbchieu.account_security_jwt.common.AppErrorResponse;
import com.vhbchieu.account_security_jwt.config.constant.AccountErrorDto;
import com.vhbchieu.account_security_jwt.exception.AccountException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    HttpServletRequest request;

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<AppErrorResponse> handleException(Exception e) {
        log.error("RequestId: {}, Exception: {}", request.getRequestId(), e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AppErrorResponse(request.getRequestId(),"Lỗi Hệ Thống"));
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    ResponseEntity<AppErrorResponse> handleException(NoResourceFoundException e) {
        log.error("RequestId: {}, NoResourceFoundException: {}", request.getRequestId(), e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AppErrorResponse(request.getRequestId(), "Không tìm thấy tài nguyên truy vấn"));
    }

    //Account exception
    @ExceptionHandler(value = AccountException.class)
    ResponseEntity<AppErrorResponse> handleAccountException(AccountException e) {
        log.error("RequestId: {}, AccountException: {}", request.getRequestId(), e.getMessage(), e);

        AppErrorResponse response = new AppErrorResponse(request.getRequestId(), AccountErrorDto.of(e.getError()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
