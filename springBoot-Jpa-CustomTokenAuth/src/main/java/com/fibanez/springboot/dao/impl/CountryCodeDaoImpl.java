package com.fibanez.springboot.dao.impl;

import com.fibanez.springboot.dao.AbstractDao;
import com.fibanez.springboot.dao.CountryCodeDao;
import com.fibanez.springboot.domain.dto.CountryCode;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("countryCodeDao")
public class CountryCodeDaoImpl extends AbstractDao<Long, CountryCode> implements CountryCodeDao {

    @Override
    public Optional<CountryCode> findById(long id) {
        CountryCode countryCode = getByKey(id);
        return Optional.ofNullable(countryCode);
    }
}
