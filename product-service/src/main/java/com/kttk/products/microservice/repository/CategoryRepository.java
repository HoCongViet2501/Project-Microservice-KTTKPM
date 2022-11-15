package com.kttk.products.microservice.repository;

import com.kttk.products.microservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}