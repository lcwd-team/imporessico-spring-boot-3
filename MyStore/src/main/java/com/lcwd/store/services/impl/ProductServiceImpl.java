package com.lcwd.store.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.store.dtos.ProductDto;
import com.lcwd.store.entities.Category;
import com.lcwd.store.entities.Product;
import com.lcwd.store.excetions.ResourceNotFountException;
import com.lcwd.store.respository.CategoryRepository;
import com.lcwd.store.respository.ProductRepository;
import com.lcwd.store.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper map;

    @Override
    public ProductDto create(ProductDto dto) {
        Product prod = map.map(dto, Product.class);
        prod.setProductId(UUID.randomUUID().toString());
        Product saveprod = productRepository.save(prod);
        return map.map(saveprod, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto dto, String productId) {
        Product prod = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product is not found"));
        prod.setDescription(dto.getDescription());
        prod.setPrice(dto.getPrice());
        prod.setTitle(dto.getTitle());
        prod.setStock(dto.isStock());
        prod.setLive(dto.isLive());
        Product prod1 = productRepository.save(prod);
        return map.map(prod1, ProductDto.class);
    }

    @Override
    public ProductDto get(String productId) {
        Product prod = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFountException("Product not found"));
        return map.map(prod, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> prod = productRepository.findAll();
        return prod.stream().map(p -> map.map(p, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(String productId) {
        Product prod = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFountException("Product with given id not found..."));
        productRepository.delete(prod);

    }

    @Override
    public List<ProductDto> searchProduct(String keywords) {
        List<Product> lp = productRepository.findByTitleContains(keywords);
        return lp.stream().map(p -> map.map(p, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDto create(ProductDto dto, String categoryId) {
        // GET THE CATEGORY OF GIVEN ID
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFountException("Category not found !!"));
        Product product = map.map(dto, Product.class);
        product.setCategory(category);
        product.setProductId(UUID.randomUUID().toString());
        Product savedProduct = productRepository.save(product);
        return map.map(savedProduct, ProductDto.class);
    }

}
