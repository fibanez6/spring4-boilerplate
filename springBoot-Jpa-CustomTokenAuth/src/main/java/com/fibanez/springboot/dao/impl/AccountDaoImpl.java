package com.fibanez.springboot.dao.impl;

import com.fibanez.springboot.dao.AbstractDao;
import com.fibanez.springboot.dao.AccountDao;
import com.fibanez.springboot.domain.dto.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("accountDao")
public class AccountDaoImpl extends AbstractDao<Long, Account> implements AccountDao {

    @Override
    public Optional<Account> findById(long id) {
        Account account = getByKey(id);
        return Optional.ofNullable(account);
    }

}
