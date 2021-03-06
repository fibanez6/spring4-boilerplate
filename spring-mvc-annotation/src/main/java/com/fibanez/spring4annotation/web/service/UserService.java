package com.fibanez.spring4annotation.web.service;

import com.fibanez.spring4annotation.model.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

    boolean isUserExist(User user);

    void deleteAllUsers();

}
