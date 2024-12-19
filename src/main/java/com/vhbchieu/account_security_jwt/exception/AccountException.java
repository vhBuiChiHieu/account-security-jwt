package com.vhbchieu.account_security_jwt.exception;

import com.vhbchieu.account_security_jwt.config.constant.AccountError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountException extends RuntimeException {
    private AccountError error;

    public AccountException(AccountError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.getMessage();
    }
}
