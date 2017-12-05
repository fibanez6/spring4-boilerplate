package com.fibanez.spring4annotation.service.impl;

import java.util.List;

import com.fibanez.spring4annotation.dao.UserProfileDao;
import com.fibanez.spring4annotation.model.UserProfile;
import com.fibanez.spring4annotation.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileDao dao;

    public UserProfile findById(int id) {
        return dao.findById(id);
    }

    public UserProfile findByType(String type){
        return dao.findByType(type);
    }

    public List<UserProfile> findAll() {
        return dao.findAll();
    }
}

