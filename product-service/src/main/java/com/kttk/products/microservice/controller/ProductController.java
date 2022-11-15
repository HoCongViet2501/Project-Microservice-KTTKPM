package com.kttk.products.microservice.controller;

import com.kttk.products.microservice.entity.Brand;
import com.kttk.products.microservice.entity.Category;
import com.kttk.products.microservice.entity.Product;
import com.kttk.products.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<?> getProductById(@PathVariable("product_id") Integer productId) {
        return ResponseEntity.ok().body(productService.getProductById(productId));
    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable("category_id") Integer categoryId) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(categoryId));
    }

    @GetMapping("/brand/{brand_id}")
    public ResponseEntity<?> getProductsByBrandId(@PathVariable("brand_id") Integer brandId) {
        return ResponseEntity.ok().body(productService.getProductsByBrand(brandId));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchProducts(@PathVariable("keyword") String keyword) {
        return ResponseEntity.ok().body(productService.searchProducts(keyword));
    }

    @GetMapping("/brand/{brand_id}/category/{category_id}")
    public ResponseEntity<?> getProductsByBrandIdAndCategoryId(@PathVariable("brand_id") Integer brandId,
                                                              @PathVariable("category_id") Integer categoryId) {
        return ResponseEntity.ok().body(productService.getProductsByCategoryAndBrand(categoryId, brandId));
    }

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.addProduct(product));
    }

    @PutMapping("")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.updateProduct(product));
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("product_id") Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().body("Product has been deleted successfully.");
    }

}
