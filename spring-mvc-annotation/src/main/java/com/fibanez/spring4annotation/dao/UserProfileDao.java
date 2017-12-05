package com.fibanez.spring4annotation.dao;

import com.fibanez.spring4annotation.model.UserProfile;

import java.util.List;


public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
