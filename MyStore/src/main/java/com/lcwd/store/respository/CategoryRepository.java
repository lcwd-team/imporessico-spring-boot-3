package com.lcwd.store.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.store.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
