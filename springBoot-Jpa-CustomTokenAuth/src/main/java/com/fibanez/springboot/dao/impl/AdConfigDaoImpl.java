package com.fibanez.springboot.dao.impl;

import com.fibanez.springboot.dao.AbstractDao;
import com.fibanez.springboot.dao.AdConfigDao;
import com.fibanez.springboot.domain.dto.AdConfig;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("adConfigDao")
public class AdConfigDaoImpl extends AbstractDao<Long, AdConfig> implements AdConfigDao {

    @Override
    public Optional<AdConfig> findById(long id) {
        AdConfig adConfig = getByKey(id);
        return Optional.ofNullable(adConfig);
    }

}
