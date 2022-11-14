package com.kttk.products.microservice.controller;

import com.kttk.products.microservice.entity.Brand;
import com.kttk.products.microservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("")
    public ResponseEntity<?> getBrands() {
        return ResponseEntity.ok().body(brandService.getAllBrands());
    }

    @GetMapping("/{brand_id}")
    public ResponseEntity<?> getBrandById(@PathVariable("brand_id") Integer brandId) {
        return ResponseEntity.ok().body(brandService.getBrandById(brandId));
    }

    @PostMapping("")
    public ResponseEntity<?> addBrand(@RequestBody Brand brand) {
        return ResponseEntity.ok().body(brandService.addBrand(brand));
    }

    @PutMapping("")
    public ResponseEntity<?> updateBrand(@RequestBody Brand brand) {
        return ResponseEntity.ok().body(brandService.updateBrand(brand));
    }

    @DeleteMapping("/{brand_id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("brand_id") Integer brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity.ok().body("Brand has been deleted successfully.");
    }
}
