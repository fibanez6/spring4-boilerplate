package com.fibanez.spring4annotation.service;

import com.fibanez.spring4annotation.model.UserProfile;

import java.util.List;


public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}

