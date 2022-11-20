package com.se.authservice.service.impl;

import com.se.backend.ecommerceapp.dto.request.AccountRequest;
import com.se.backend.ecommerceapp.dto.response.AccountResponse;
import com.se.backend.ecommerceapp.dto.response.LoginResponse;
import com.se.backend.ecommerceapp.exceptions.ForbiddenException;
import com.se.backend.ecommerceapp.mapping.MapData;
import com.se.backend.ecommerceapp.model.entity.Account;
import com.se.backend.ecommerceapp.model.enums.Role;
import com.se.backend.ecommerceapp.repository.AccountRepository;
import com.se.backend.ecommerceapp.security.jwt.JwtProvider;
import com.se.backend.ecommerceapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    
    private final AccountRepository accountRepository;
    
    private final AuthenticationManager authenticationManager;
    
    private final JwtProvider jwtProvider;
    
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public AuthServiceImpl(AccountRepository accountRepository, AuthenticationManager authenticationManager, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public LoginResponse login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            String token = jwtProvider.createToken(username, String.valueOf(role));
            return new LoginResponse(role, token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new ForbiddenException("username or password is incorrect!");
        }
    }
    
    
    @Override
    public AccountResponse register(AccountRequest accountRequest) {
        Account account = MapData.mapOne(accountRequest, Account.class);
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        account.setCreatedDate(new Date());
        account.setRole(Role.valueOf(accountRequest.getRole()));
        Account accountSaved = this.accountRepository.save(account);
        return MapData.mapOne(accountSaved, AccountResponse.class);
    }
    
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
    }
}
