package com.lcwd.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.store.dtos.ApiResponse;
import com.lcwd.store.dtos.CategoryDto;
import com.lcwd.store.dtos.ProductDto;
import com.lcwd.store.services.CategoryService;
import com.lcwd.store.services.ProductService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    // create

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto dto) {
        CategoryDto createCat = categoryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCat);
    }

    @PostMapping("/{categoryId}/products")
    public ResponseEntity<ProductDto> createProduct(
            @PathVariable String categoryId,
            @RequestBody ProductDto dto) {
        ProductDto product = productService.create(dto, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // update

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> update(@PathVariable String categoryId, @RequestBody CategoryDto dto) {
        CategoryDto updatedCat = categoryService.update(categoryId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCat);
    }

    // getall

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        java.util.List<CategoryDto> all = categoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    // get single

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> get(@PathVariable String categoryId) {
        CategoryDto cat = categoryService.get(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(cat);
    }

    // delete.
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String categoryId) {
        categoryService.delete(categoryId);
        ApiResponse response = ApiResponse.builder()
                .message("Category Deleted Success !!")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }

}
