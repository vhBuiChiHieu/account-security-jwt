package com.vhbchieu.account_security_jwt.sys.repos;

import com.vhbchieu.account_security_jwt.sys.domain.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
