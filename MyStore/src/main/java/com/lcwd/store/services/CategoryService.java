package com.lcwd.store.services;

import java.util.List;

import com.lcwd.store.dtos.CategoryDto;

public interface CategoryService {

    // create
    CategoryDto create(CategoryDto categoryDto);

    // getall
    List<CategoryDto> getAll();

    // Assigment
    // update
    CategoryDto update(String categoryId, CategoryDto dto);

    // get single

    CategoryDto get(String categoryId);

    // delete
    void delete(String categoryId);
}
