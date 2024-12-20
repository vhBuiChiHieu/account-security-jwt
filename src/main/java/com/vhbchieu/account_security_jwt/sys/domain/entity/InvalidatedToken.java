package com.vhbchieu.account_security_jwt.sys.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvalidatedToken {
    @Id
    private String id;
    //Sẽ quyét xóa các token hết hạn
    private Date expiryDate;
}
