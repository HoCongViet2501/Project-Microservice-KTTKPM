package com.kttk.services.address.controller;

import com.kttk.services.address.entity.Address;
import com.kttk.services.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/user/{userId}")
    public List<Address> getAllAddresses(@PathVariable Integer userId) {
        return addressService.getAllAddresses(userId);
    }

    @GetMapping("/user/{userId}/default")
    public String getDefaultAddress(@PathVariable Integer userId) {
        return addressService.getDefaultAddress(userId);
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @PostMapping("")
    public Address addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Integer id, @RequestBody Address address) {
        address.setId(id);
        return addressService.updateAddress(address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
    }
}
