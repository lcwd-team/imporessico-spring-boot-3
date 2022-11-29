package com.lcwd.store.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
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
import com.lcwd.store.dtos.ProductDto;
import com.lcwd.store.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    // create product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto productDto2 = productService.create(productDto);
        return new ResponseEntity<ProductDto>(productDto2, HttpStatus.CREATED);
    }

    // get single product
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String productId) {
        ProductDto product = productService.get(productId);
        return ResponseEntity.ok(product);
    }

    // get all products
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    // delete product
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String productId) {
        productService.delete(productId);
        return ResponseEntity.ok(ApiResponse.builder().message("Product is deleted").success(true).build());
    }

    // update product
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,
            @PathVariable String productId) {
        ProductDto productDto2 = productService.update(productDto, productId);
        return new ResponseEntity<ProductDto>(productDto2, HttpStatus.OK);
    }

    // search products
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ProductDto>> searchProduct(@PathVariable String keyword) {
        List<ProductDto> product = productService.searchProduct(keyword);
        return ResponseEntity.ok(product);
    }
}
