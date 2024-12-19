package com.vhbchieu.account_security_jwt.config.constant;

import lombok.Getter;

@Getter
public enum AccountError {
    USERNAME_ALREADY_EXISTS(10, "Tên tài khoản đã tồn tại"),
    USER_NOT_EXIST(11,"Tài khoản không tồn tại")
    ;

    private final int code;
    private final String message;

    AccountError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
