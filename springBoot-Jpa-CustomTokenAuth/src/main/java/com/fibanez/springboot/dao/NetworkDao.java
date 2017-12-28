package com.fibanez.springboot.dao;

import com.fibanez.springboot.domain.dto.Network;

import java.util.Optional;

public interface NetworkDao {

    Optional<Network> findById(long id);

}
