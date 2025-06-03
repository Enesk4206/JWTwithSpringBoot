package com.LT1.FirstTouch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LT1.FirstTouch.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
