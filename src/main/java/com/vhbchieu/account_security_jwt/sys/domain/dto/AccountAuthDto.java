package com.vhbchieu.account_security_jwt.sys.domain.dto;

import com.vhbchieu.account_security_jwt.config.constant.Role;
import com.vhbchieu.account_security_jwt.sys.domain.entity.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountAuthDto implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;

    private Role role;

    private Date updateTime;

    public AccountAuthDto(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.email = account.getEmail();
        this.phone = account.getPhone();
        this.role = account.getRole();
        this.updateTime = account.getUpdateTime();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role.name()); // Prefix ROLE_ để phù hợp với quy ước của Spring Security
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
