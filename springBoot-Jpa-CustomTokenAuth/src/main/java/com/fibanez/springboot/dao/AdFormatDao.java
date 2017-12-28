package com.fibanez.springboot.dao;

import com.fibanez.springboot.domain.dto.AdFormat;

import java.util.Optional;

public interface AdFormatDao {

    Optional<AdFormat> findById(long id);

}
