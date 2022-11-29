package com.lcwd.store.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.store.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByTitleContains(String keyword);
}
