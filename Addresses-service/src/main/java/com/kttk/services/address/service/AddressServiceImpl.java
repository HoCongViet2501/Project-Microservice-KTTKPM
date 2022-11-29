package com.kttk.services.address.service;

import com.kttk.services.address.entity.Address;
import com.kttk.services.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;


    @Override
    @Cacheable(value = "address", key = "#userId")
    public String getDefaultAddress(Integer userId) {
        List<Address> list = addressRepository.findAllByUserId(userId);
        for (Address address : list) {
            if (address.getIsDefault()) {
                return address.getAddress();
            }
        }
        return null;
    }

    @Override
    public List<Address> getAllAddresses(Integer userId) {
        return addressRepository.findAllByUserId(userId);
    }

    @Override
    @Cacheable(value = "address", key = "#id")
    public Address getAddressById(Integer id) {
        return addressRepository.findById(id).get();
    }

    @Override
    @CachePut(value = "address", key = "#address.id")
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @CachePut(value = "address", key = "#address.id")
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @CacheEvict(value = "address", key = "#id")
    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }
}
