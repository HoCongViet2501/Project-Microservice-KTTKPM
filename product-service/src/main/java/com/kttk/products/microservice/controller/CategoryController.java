package com.kttk.products.microservice.controller;

import com.kttk.products.microservice.entity.Category;
import com.kttk.products.microservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("category_id") Integer categoryId) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(categoryId));
    }

    @PostMapping("")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok().body(categoryService.addCategory(category));
    }

    @PutMapping("")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        return ResponseEntity.ok().body(categoryService.updateCategory(category));
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("category_id") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().body("Category has been deleted successfully.");
    }
}
