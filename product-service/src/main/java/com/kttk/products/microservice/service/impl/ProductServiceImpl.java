package com.kttk.products.microservice.service.impl;

import com.kttk.products.microservice.entity.Product;
import com.kttk.products.microservice.repository.ProductRepository;
import com.kttk.products.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByCategory(Integer id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    public List<Product> getProductsByBrand(Integer id) {
        return productRepository.findByBrandId(id);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(Integer categoryId, Integer brandId) {
        return productRepository.findByCategoryIdAndBrandId(categoryId, brandId);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }
}
