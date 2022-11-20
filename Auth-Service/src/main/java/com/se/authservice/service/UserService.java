package com.se.authservice.service;

import com.se.authservice.dto.request.UserRequest;
import com.se.authservice.dto.response.UserResponse;
import com.se.authservice.model.entity.User;

import java.util.List;

public interface UserService {

    UserResponse findUserById(Long id);

    List<UserResponse> findAllUser();

    UserResponse createUser(UserRequest user);

    UserResponse updateUser(UserRequest userRequest, Long id);

    void delete(Long id);

    UserResponse ownProfile();

    UserResponse updateOwnProfile(UserRequest userRequest);
}
