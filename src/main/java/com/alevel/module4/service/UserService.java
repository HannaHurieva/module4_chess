package com.alevel.module4.service;

import com.alevel.module4.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll(boolean done);

    Optional<User> findById(Long id);
    User findByUsername(String username);

    Long save(User user);
    void delete(long id);
}
