package com.kttk.services.address.repository;

import com.kttk.services.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByUserId(Integer userId);
}