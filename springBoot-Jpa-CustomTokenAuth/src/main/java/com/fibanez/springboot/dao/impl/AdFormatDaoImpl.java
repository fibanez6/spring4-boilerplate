package com.fibanez.springboot.dao.impl;

import com.fibanez.springboot.dao.AbstractDao;
import com.fibanez.springboot.dao.AdFormatDao;
import com.fibanez.springboot.domain.dto.AdFormat;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("adFormatDao")
public class AdFormatDaoImpl extends AbstractDao<Long, AdFormat> implements AdFormatDao {

    @Override
    public Optional<AdFormat> findById(long id) {
        AdFormat adFormat = getByKey(id);
        return Optional.ofNullable(adFormat);
    }

}
