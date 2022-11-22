package com.kttk.services.address.controller;

import com.kttk.services.address.entity.Address;
import com.kttk.services.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin
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
