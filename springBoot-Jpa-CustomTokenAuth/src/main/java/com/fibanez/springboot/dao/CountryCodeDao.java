package com.fibanez.springboot.dao;

import com.fibanez.springboot.domain.dto.CountryCode;

import java.util.Optional;

public interface CountryCodeDao {

    Optional<CountryCode> findById(long id);

}
