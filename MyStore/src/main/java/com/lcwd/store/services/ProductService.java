package com.lcwd.store.services;

import java.util.List;

import com.lcwd.store.dtos.ProductDto;

public interface ProductService {

    // create
    ProductDto create(ProductDto dto);

    ProductDto create(ProductDto dto, String categoryId);

    // update
    ProductDto update(ProductDto dto, String productId);

    // get
    ProductDto get(String productId);

    // get all
    List<ProductDto> getAll();

    // delete
    void delete(String productId);

    // search
    List<ProductDto> searchProduct(String keywords);

    // add more operations as required

}
