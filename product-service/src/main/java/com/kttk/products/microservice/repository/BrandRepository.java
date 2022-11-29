package com.kttk.products.microservice.repository;

import com.kttk.products.microservice.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

@Repository
@RedisHash("Brand")
public interface BrandRepository extends JpaRepository<Brand, Integer>{
}
