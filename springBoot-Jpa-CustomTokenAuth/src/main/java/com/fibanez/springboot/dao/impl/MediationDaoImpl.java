package com.fibanez.springboot.dao.impl;

import com.fibanez.springboot.dao.AbstractDao;
import com.fibanez.springboot.dao.MediationDao;
import com.fibanez.springboot.domain.dto.Mediation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("mediationDao")
public class MediationDaoImpl extends AbstractDao<Long, Mediation> implements MediationDao {

    @Override
    public Optional<Mediation> findById(long id) {
        Mediation mediation = getByKey(id);
        return Optional.ofNullable(mediation);
    }
}
