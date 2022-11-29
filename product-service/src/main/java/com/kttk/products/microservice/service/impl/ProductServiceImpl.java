package com.kttk.products.microservice.service.impl;

import com.kttk.products.microservice.entity.Product;
import com.kttk.products.microservice.repository.ProductRepository;
import com.kttk.products.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    @Cacheable(value = "Product", key = "#id")
    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Cacheable(value = "Products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @CachePut(value = "Product")
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @CachePut(value = "Product", key = "#product.id")
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @CacheEvict(value = "Product", key = "#id")
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "Products")
    public List<Product> getProductsByCategory(Integer id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    @Cacheable(value = "ProductsByBrand", key = "#id")
    public List<Product> getProductsByBrand(Integer id) {
        return productRepository.findByBrandId(id);
    }

    @Override
    @Cacheable(value = "ProductsByCategoryAndBrand", key = "#categoryId + #brandId")
    public List<Product> getProductsByCategoryAndBrand(Integer categoryId, Integer brandId) {
        return productRepository.findByCategoryIdAndBrandId(categoryId, brandId);
    }

    @Override
    @Cacheable(value = "Products")
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }
}
