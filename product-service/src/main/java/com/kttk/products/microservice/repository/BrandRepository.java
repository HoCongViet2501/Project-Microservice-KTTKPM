package com.kttk.products.microservice.repository;

import com.kttk.products.microservice.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}