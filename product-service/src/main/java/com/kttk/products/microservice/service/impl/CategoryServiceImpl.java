package com.kttk.products.microservice.service.impl;

import com.kttk.products.microservice.entity.Category;
import com.kttk.products.microservice.repository.CategoryRepository;
import com.kttk.products.microservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    @Cacheable(value = "Category", key = "#id")
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    @Cacheable(value = "Categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @CachePut(value = "Category", key = "#category.id")
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @CachePut(value = "Category", key = "#category.id")
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @CacheEvict(value = "Category", key = "#id")
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}
