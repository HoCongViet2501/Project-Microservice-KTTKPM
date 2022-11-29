package com.se.authservice.service.impl;

import com.se.authservice.dto.request.AccountRequest;
import com.se.authservice.dto.response.AccountResponse;
import com.se.authservice.dto.response.LoginResponse;
import com.se.authservice.dto.response.UserResponse;
import com.se.authservice.exceptions.ForbiddenException;
import com.se.authservice.mapping.MapData;
import com.se.authservice.model.entity.Account;
import com.se.authservice.model.entity.User;
import com.se.authservice.model.enums.Role;
import com.se.authservice.repository.AccountRepository;
import com.se.authservice.repository.UserRepository;
import com.se.authservice.security.jwt.JwtProvider;
import com.se.authservice.service.AuthService;
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

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(AccountRepository accountRepository, AuthenticationManager authenticationManager, JwtProvider jwtProvider, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
        User user = new User();
        user.setAccount(accountSaved);
        this.userRepository.save(user);
        AccountResponse accountResponse = MapData.mapOne(accountSaved, AccountResponse.class);
        accountResponse.setUser(MapData.mapOne(user, UserResponse.class));
        return accountResponse;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
    }
}
