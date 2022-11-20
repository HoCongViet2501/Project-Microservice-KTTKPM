package com.se.authservice.service.impl;

import com.se.backend.ecommerceapp.exceptions.ResourceNotFoundException;
import com.se.backend.ecommerceapp.model.entity.User;
import com.se.backend.ecommerceapp.repository.UserRepository;
import com.se.backend.ecommerceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    @Cacheable(value = "User", key = "#id")
    public User findUser(Long id) {
        // TODO Auto-generated method stub
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("not found user"));
    }
    
    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }
    
    @Override
    @Cacheable(value = "User")
    public User save(User user) {
        // TODO Auto-generated method stub
        return userRepository.saveAndFlush(user);
    }
    
    @Override
    @CachePut(value = "User", key = "#id")
    public User update(User user) {
        // TODO Auto-generated method stub
        return userRepository.saveAndFlush(user);
    }
    
    @Override
    @CacheEvict(value = "User", key = "#id")
    public void delete(User user) {
        // TODO Auto-generated method stub
        userRepository.delete(user);
    }
    
}
