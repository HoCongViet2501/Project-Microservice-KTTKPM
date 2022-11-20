package com.se.authservice.service;

import com.se.backend.ecommerceapp.model.entity.User;

import java.util.List;

public interface UserService {

	User findUser(Long id);
    List<User> findAll();
    User save(User user);
    User update(User user);
    void delete(User user);
}
