package com.fibanez.springboot.dao.impl;

import com.fibanez.springboot.dao.AbstractDao;
import com.fibanez.springboot.dao.ApplicationDao;
import com.fibanez.springboot.domain.dto.Application;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("applicationDao")
public class ApplicationDaoImpl extends AbstractDao<Long, Application> implements ApplicationDao {

    @Override
    public Optional<Application> findById(long id) {
        Application application = getByKey(id);
        return Optional.ofNullable(application);
    }

}
