package com.kttk.products.microservice.service.impl;

import com.kttk.products.microservice.entity.Brand;
import com.kttk.products.microservice.repository.BrandRepository;
import com.kttk.products.microservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public BrandServiceImpl() {
        super();
    }

    @Override
    @Cacheable(value = "Brand", key = "#id")
    public Brand getBrandById(Integer id) {
        return brandRepository.findById(id).get();
    }

    @Override
    @Cacheable(value = "Brand")
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    @Cacheable(value = "Brand")
    public Brand addBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    @CachePut(value = "Brand", key = "#brand.id")
    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    @CacheEvict(value = "Brand", key = "#id")
    public void deleteBrand(Integer id) {
        brandRepository.deleteById(id);
    }
}
