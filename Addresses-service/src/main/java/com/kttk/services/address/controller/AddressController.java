package com.kttk.services.address.controller;

import com.kttk.services.address.entity.Address;
import com.kttk.services.address.service.AddressService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin
@CircuitBreaker(name = "service-java")
@Retry(name = "service-java")
@RateLimiter(name = "service-java")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getAllAddresses(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(addressService.getAllAddresses(userId));
    }

    @GetMapping("/user/{userId}/default")
    public ResponseEntity<Object> getDefaultAddress(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(addressService.getDefaultAddress(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAddressById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(addressService.getAddressById(id));
    }

    @PostMapping
    public ResponseEntity<Object> addAddress(@RequestBody Address address) {
        return ResponseEntity.ok().body(addressService.addAddress(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
        address.setId(id);
        return ResponseEntity.ok().body(addressService.updateAddress(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAddress(@PathVariable Integer id) {
        return ResponseEntity.ok().body(true);
    }
}
