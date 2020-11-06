package com.sign.sign.service.impl;

import com.sign.sign.dao.UserRepository;
import com.sign.sign.entity.User;
import com.sign.sign.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository){
        this.repository= repository;
    }

    @Override
    public boolean register(User user) {
        User u = repository.findByEmail(user.getEmail());
        if (u!=null){
            return false;
        }
        user.setSalt(UUID.randomUUID().toString());
        if (user.getPassword()!=null) {
            user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword() + user.getSalt()).getBytes()));
        }
        repository.save(user);
        return true;
    }

    @Override
    public User login(User user) {
        User u=repository.findByEmail(user.getEmail());
        if (u==null){
            return null;
        }
        if ((DigestUtils.md5DigestAsHex((user.getPassword()+u.getSalt()).getBytes())).equals(u.getPassword())){
            return u;
        }
        return null;
    }

    @Override
    public User findUser(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public boolean modifyPassword(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword() + user.getSalt()).getBytes()));
        repository.save(user);
        return true;
    }
}