package com.se.authservice.controller;

import com.se.backend.ecommerceapp.model.entity.User;
import com.se.backend.ecommerceapp.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users ")
@Retry(name="service-java")
@CircuitBreaker(name="service-java")
public class UserController {
	@Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping
    public void delete(@RequestBody User user) {
    	userService.delete(user);

    }

}
