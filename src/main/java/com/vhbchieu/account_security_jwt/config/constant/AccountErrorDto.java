package com.vhbchieu.account_security_jwt.config.constant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountErrorDto {
    private int code;
    private String message;

    public AccountErrorDto(AccountError accountError) {
        this.code = accountError.getCode();
        this.message = accountError.getMessage();
    }

    public static AccountErrorDto of(AccountError accountError) {
        return new AccountErrorDto(accountError);
    }
}
