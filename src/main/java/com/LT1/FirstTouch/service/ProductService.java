package com.LT1.FirstTouch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LT1.FirstTouch.model.Product;
import com.LT1.FirstTouch.repo.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }
}
