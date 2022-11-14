package com.kttk.products.microservice.service;

import com.kttk.products.microservice.entity.Brand;

import java.util.List;

public interface BrandService {
    public Brand getBrandById(Integer id);
    public List<Brand> getAllBrands();
    public Brand addBrand(Brand brand);
    public Brand updateBrand(Brand brand);
    public void deleteBrand(Integer id);
}
