package com.fibanez.springboot.dao;

import com.fibanez.springboot.domain.dto.Mediation;

import java.util.Optional;

public interface MediationDao {

    Optional<Mediation> findById(long id);

}
