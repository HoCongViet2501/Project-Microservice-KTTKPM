package com.kttk.services.address.service;

import com.kttk.services.address.entity.Address;

import java.util.List;

public interface AddressService {
    public String getDefaultAddress(Integer userId);
    public List<Address> getAllAddresses(Integer userId);
    public Address getAddressById(Integer id);
    public Address addAddress(Address address);
    public Address updateAddress(Address address);
    public void deleteAddress(Integer id);
}
