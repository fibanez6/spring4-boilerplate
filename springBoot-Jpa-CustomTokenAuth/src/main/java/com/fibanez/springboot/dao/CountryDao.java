package com.fibanez.springboot.dao;

import com.fibanez.springboot.domain.dto.Country;

import java.util.Optional;

public interface CountryDao {

    Optional<Country> findById(long id);

    Optional<Country> findByCode(String code);
}
