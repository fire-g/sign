package com.sign.sign.service;

import com.sign.sign.entity.User;

public interface UserService {
    boolean register(User user);
    User login(User user);
    User findUser(String email);
    boolean modifyPassword(User user);
}
