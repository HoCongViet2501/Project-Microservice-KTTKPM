package com.kttk.products.microservice.controller;

import com.kttk.products.microservice.entity.Brand;
import com.kttk.products.microservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("")
    public ResponseEntity<List<Brand>> getBrands() {
        return ResponseEntity.ok().body(brandService.getAllBrands());
    }

    @GetMapping("/{brand_id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable("brand_id") Integer brandId) {
        return ResponseEntity.ok().body(brandService.getBrandById(brandId));
    }

    @PostMapping("")
    public ResponseEntity<Brand> addBrand(@RequestBody Brand brand) {
        return ResponseEntity.ok().body(brandService.addBrand(brand));
    }

    @PutMapping("/{brand_id}")
    public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand, @PathVariable("brand_id") Integer brandId) {
        brand.setId(brandId);
        return ResponseEntity.ok().body(brandService.updateBrand(brand));
    }

    @DeleteMapping("/{brand_id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("brand_id") Integer brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity.ok().body("Brand has been deleted successfully.");
    }
}
