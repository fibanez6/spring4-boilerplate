package com.fibanez.springboot.dao;

import com.fibanez.springboot.domain.dto.NetworkTokenAuth;

import java.util.Optional;

public interface NetworkTokenAuthDao {

    Optional<NetworkTokenAuth> findById(long id);

    Optional<NetworkTokenAuth> findByToken(String token);

}
