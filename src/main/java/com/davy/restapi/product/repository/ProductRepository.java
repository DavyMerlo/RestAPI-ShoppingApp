package com.davy.restapi.product.repository;

import com.davy.restapi.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>,
        CustomProductRepository{
}