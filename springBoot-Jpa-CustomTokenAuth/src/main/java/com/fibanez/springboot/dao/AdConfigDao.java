package com.fibanez.springboot.dao;

import com.fibanez.springboot.domain.dto.AdConfig;

import java.util.Optional;

public interface AdConfigDao {

    Optional<AdConfig> findById(long id);

}
