package com.fibanez.springboot.dao;

import com.fibanez.springboot.domain.dto.Account;

import java.util.Optional;

public interface AccountDao {

    Optional<Account> findById(long id);

}
