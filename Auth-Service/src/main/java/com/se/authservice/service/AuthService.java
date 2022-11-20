package com.se.authservice.service;

import com.se.backend.ecommerceapp.dto.request.AccountRequest;
import com.se.backend.ecommerceapp.dto.response.AccountResponse;
import com.se.backend.ecommerceapp.dto.response.LoginResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    LoginResponse login(String username, String password);
    
    AccountResponse register(AccountRequest accountRequest);
    
    void logout(HttpServletRequest request, HttpServletResponse response);
    
    
}
