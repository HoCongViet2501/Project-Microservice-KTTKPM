package com.kttk.products.microservice.service;

import com.kttk.products.microservice.entity.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(Integer id);
    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(Integer id);
    public List<Product> getProductsByCategory(Integer id);
    public List<Product> getProductsByBrand(Integer id);
    public List<Product> getProductsByCategoryAndBrand(Integer categoryId, Integer brandId);
    public List<Product> searchProducts(String keyword);
}
