package com.kttk.products.microservice.service;

import com.kttk.products.microservice.entity.Category;

import java.util.List;

public interface CategoryService {
    public Category getCategoryById(Integer id);
    public List<Category> getAllCategories();
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(Integer id);
}
