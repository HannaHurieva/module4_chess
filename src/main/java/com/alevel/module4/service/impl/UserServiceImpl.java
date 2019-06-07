package com.alevel.module4.service.impl;

import com.alevel.module4.entity.User;
import com.alevel.module4.repository.UserRepo;
import com.alevel.module4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> findAll(boolean done) {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User findByUsername(String username) {
    return userRepo.findByUsername(username);
    }

    @Override
    public Long save(User user) {
        return userRepo.save(user).getId();
    }

    @Override
    public void delete(long id) {
        userRepo.deleteById(id);
    }
}
