package com.fibanez.spring4annotation.dao;

import com.fibanez.spring4annotation.model.User;

import java.util.List;

public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    void deleteAllUsers();

    List<User> findAllUsers();

}