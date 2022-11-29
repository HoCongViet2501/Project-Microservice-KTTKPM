package com.se.authservice.controller;

import com.se.authservice.dto.request.AccountRequest;
import com.se.authservice.dto.request.LoginRequest;
import com.se.authservice.dto.response.AccountResponse;
import com.se.authservice.dto.response.LoginResponse;
import com.se.authservice.service.AuthService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@CircuitBreaker(name = "service-java")
@RateLimiter(name = "service-java")
public class AuthController {
    private final AuthService authenticationService;


    @Autowired
    public AuthController(AuthService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/login")
    @Retry(name = "service-java")
    @Operation(summary = "login for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "login successfully!"),
            @ApiResponse(responseCode = "403", description = "incorrect username or password")
    })
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        String password = loginRequest.getPassword();
        LoginResponse loginResponse = this.authenticationService.login(loginRequest.getUsername(), password);
        return ResponseEntity.accepted().body(loginResponse.getToken());
    }

    @Operation(summary = "register for new user")
    @PostMapping("/register")
    @Retry(name = "service-java", fallbackMethod = "registerFallback")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create New User successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request!")
    })
    public ResponseEntity<Object> register(@Valid @RequestBody AccountRequest accountRequest) {
        AccountResponse accountResponse = this.authenticationService.register(accountRequest);
        return ResponseEntity.ok().body(accountResponse);
    }

    @Operation(summary = "logout for user")
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.authenticationService.logout(httpServletRequest, httpServletResponse);
        return ResponseEntity.ok("Logout.user.successfully!");
    }

    public ResponseEntity<String> fallBackLogin(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fallback method called !Cannot login now");
    }

    public ResponseEntity<String> registerFallback(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fallback method called !Cannot register now");
    }
}
