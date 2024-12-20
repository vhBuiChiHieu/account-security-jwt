package com.vhbchieu.account_security_jwt.config.constant;

import lombok.Getter;

@Getter
public enum AccountError {
    USERNAME_ALREADY_EXISTS(10, "Tên tài khoản đã tồn tại"),
    USER_NOT_EXIST(11,"Tài khoản không tồn tại"),
    PASSWORD_INCORRECT(12, "Mật khẩu không chính xác"),
    ACCOUNT_NOT_FOUND(14,"Không tìm thấy tài khoản"),
    TOKEN_INVALID(24, "Token Không hợp lệ"),
    TOKEN_EXPIRED(25,"Token đã hết hạn"),
    TOKEN_UNSUPPORTED(26, "Loại token không được hỗ trợ"),
    TOKEN_CLAIMS_EMPTY(27, "Nội dung token trống")
    ;

    private final int code;
    private final String message;

    AccountError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
