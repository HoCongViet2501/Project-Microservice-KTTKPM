package com.se.authservice.dto.response;

import com.se.authservice.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private String username;
    
    private String role;

    private UserResponse user;
}

