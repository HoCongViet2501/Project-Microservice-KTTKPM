package com.se.authservice.controller;

import com.se.authservice.dto.request.UserRequest;
import com.se.authservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users ")
@Retry(name = "service-java")
@CircuitBreaker(name = "service-java")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    @Operation(summary = "find user by id")
    public ResponseEntity<Object> findUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "get all user")
    public ResponseEntity<Object> findAll() {
        System.err.println("get all user");
        return ResponseEntity.ok().body(userService.findAllUser());
    }

    @PostMapping("/create")
    @Operation(summary = " create new user")
    public ResponseEntity<Boolean> save(@RequestBody UserRequest user) {
        return ResponseEntity.ok().body(userService.createUser(user) != null);
    }

    @PutMapping("{id}")
    @Operation(summary = "update user")
    public ResponseEntity<Boolean> update(@RequestBody UserRequest user, @PathVariable Long id) {
        return ResponseEntity.ok().body(userService.updateUser(user, id) != null);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete user by id")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/me")
    @Operation(summary = "get current user")
    public ResponseEntity<Object> ownProfile() {
        return ResponseEntity.ok().body(userService.ownProfile());
    }

    @PutMapping("/me")
    @Operation(summary = "update current user")
    public ResponseEntity<Boolean> updateOwnProfile(@RequestBody UserRequest user) {
        System.err.println(user.getFullName());
        return ResponseEntity.ok().body(userService.updateOwnProfile(user) != null);
    }

}
