package com.lcwd.store.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.store.dtos.CategoryDto;
import com.lcwd.store.entities.Category;
import com.lcwd.store.excetions.ResourceNotFountException;
import com.lcwd.store.respository.CategoryRepository;
import com.lcwd.store.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        Category category = modelMapper.map(categoryDto, Category.class);
        category.setId(UUID.randomUUID().toString());
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryDto> categories = categoryRepository
                .findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return categories;
    }

    @Override
    public CategoryDto update(String categoryId, CategoryDto dto) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFountException("Category of given id not found !!"));
        category.setTitle(dto.getTitle());
        category.setDescription(dto.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto get(String categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFountException("Category of given id not found !!"));

        return modelMapper.map(category, CategoryDto.class);

    }

    @Override
    public void delete(String categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFountException("Category of given id not found !!"));

        categoryRepository.delete(category);
    }

}
