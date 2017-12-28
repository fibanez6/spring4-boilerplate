package com.fibanez.springboot.dao;

import com.fibanez.springboot.domain.dto.Application;

import java.util.Optional;

public interface ApplicationDao {

    Optional<Application> findById(long id);

}