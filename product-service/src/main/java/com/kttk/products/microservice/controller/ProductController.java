package com.kttk.products.microservice.controller;

import com.kttk.products.microservice.dto.request.ProductRequest;
import com.kttk.products.microservice.entity.Brand;
import com.kttk.products.microservice.entity.Category;
import com.kttk.products.microservice.entity.Product;
import com.kttk.products.microservice.service.BrandService;
import com.kttk.products.microservice.service.CategoryService;
import com.kttk.products.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    @GetMapping("")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable("product_id") Integer productId) {
        return ResponseEntity.ok().body(productService.getProductById(productId));
    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable("category_id") Integer categoryId) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(categoryId));
    }

    @GetMapping("/brand/{brand_id}")
    public ResponseEntity<List<Product>> getProductsByBrandId(@PathVariable("brand_id") Integer brandId) {
        return ResponseEntity.ok().body(productService.getProductsByBrand(brandId));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable("keyword") String keyword) {
        return ResponseEntity.ok().body(productService.searchProducts(keyword));
    }

    @GetMapping("/brand/{brand_id}/category/{category_id}")
    public ResponseEntity<List<Product>> getProductsByBrandIdAndCategoryId(@PathVariable("brand_id") Integer brandId,
                                                                  @PathVariable("category_id") Integer categoryId) {
        return ResponseEntity.ok().body(productService.getProductsByCategoryAndBrand(categoryId, brandId));
    }

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest product) {
        Brand brand = brandService.getBrandById(product.getBrandId());
        Category category = categoryService.getCategoryById(product.getCategoryId());
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setImageUrl(product.getImageUrl());
        newProduct.setPrice(product.getPrice());
        newProduct.setUnitsInStock(product.getUnitsInStock());
        newProduct.setCategory(category);
        newProduct.setBrand(brand);
        newProduct.setIsActive(product.getIsActive());

        return ResponseEntity.ok().body(productService.addProduct(newProduct));
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequest product, @PathVariable("product_id") Integer productId) {
        Brand brand = brandService.getBrandById(product.getBrandId());
        Category category = categoryService.getCategoryById(product.getCategoryId());
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setImageUrl(product.getImageUrl());
        newProduct.setPrice(product.getPrice());
        newProduct.setUnitsInStock(product.getUnitsInStock());
        newProduct.setCategory(category);
        newProduct.setBrand(brand);
        newProduct.setIsActive(product.getIsActive());
        return ResponseEntity.ok().body(productService.updateProduct(newProduct));
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("product_id") Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().body("Product has been deleted successfully.");
    }

}
