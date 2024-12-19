package com.vhbchieu.account_security_jwt.sys.repos;

import com.vhbchieu.account_security_jwt.sys.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> getAccountByUsername(String username);

    Account findAccountByUsername(String username);

    Account getAccountById(Long id);

    Account findAccountById(Long id);
}
