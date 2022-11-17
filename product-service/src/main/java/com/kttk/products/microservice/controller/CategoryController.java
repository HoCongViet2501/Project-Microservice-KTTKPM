package com.kttk.products.microservice.controller;

import com.kttk.products.microservice.entity.Category;
import com.kttk.products.microservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("category_id") Integer categoryId) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(categoryId));
    }

    @PostMapping("")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok().body(categoryService.addCategory(category));
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("category_id") Integer categoryId) {
        category.setId(categoryId);
        return ResponseEntity.ok().body(categoryService.updateCategory(category));
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("category_id") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().body("Category has been deleted successfully.");
    }
}
