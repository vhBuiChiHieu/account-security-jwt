package com.vhbchieu.account_security_jwt.sys.domain.entity;

import com.vhbchieu.account_security_jwt.config.constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;

    private Role role;

    @Column(name = "update_time")
    private Date updateTime;
}
