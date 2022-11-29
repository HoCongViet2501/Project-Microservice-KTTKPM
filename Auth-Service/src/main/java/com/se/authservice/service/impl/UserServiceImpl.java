package com.se.authservice.service.impl;

import com.se.authservice.dto.request.UserRequest;
import com.se.authservice.dto.response.UserResponse;
import com.se.authservice.exceptions.ResourceNotFoundException;
import com.se.authservice.mapping.MapData;
import com.se.authservice.model.entity.Account;
import com.se.authservice.model.entity.User;
import com.se.authservice.repository.AccountRepository;
import com.se.authservice.repository.UserRepository;
import com.se.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    @Cacheable(value = "user", key = "#id")
    public UserResponse findUserById(Long id) {
        // TODO Auto-generated method stub
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("not found user"));
        return MapData.mapOne(user, UserResponse.class);
    }

    @Override
    @Cacheable(value = "users")
    public List<UserResponse> findAllUser() {
        // TODO Auto-generated method stub
        return MapData.mapList(this.userRepository.findAll(), UserResponse.class);
    }

    @Override
    @Cacheable(value = "user")
    public UserResponse createUser(UserRequest user) {
        // TODO Auto-generated method stub
            User newUser = MapData.mapOne(user, User.class);
            User userSaved = this.userRepository.save(newUser);
            return MapData.mapOne(userSaved, UserResponse.class);
    }

    @Override
    @CachePut(value = "user", key = "#id")
    public UserResponse updateUser(UserRequest userRequest, Long id) {
        // TODO Auto-generated method stub
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("not found user"));
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        return MapData.mapOne(this.userRepository.save(user), UserResponse.class);
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public void delete(Long id) {
        // TODO Auto-generated method stub
        this.userRepository.delete(this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("not found user")));
    }

    @Override
    @Cacheable(value = "user")
    public UserResponse ownProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByAccount_Username(username).orElseThrow(
                () -> new ResourceNotFoundException("not found user"));
        return MapData.mapOne(user, UserResponse.class);
    }

    @Override
    @CachePut(value = "user")
    public UserResponse updateOwnProfile(UserRequest userRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = this.accountRepository.findAccountByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("not found account"));
        User user = this.userRepository.findByAccount_Id(account.getId()).orElseThrow(
                () -> new ResourceNotFoundException("not found user"));
        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getFullName());
        return MapData.mapOne(this.userRepository.save(user), UserResponse.class);
    }

}
