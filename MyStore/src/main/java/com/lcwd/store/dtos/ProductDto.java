package com.lcwd.store.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productId;

    @NotBlank(message = "title is required!! ")
    private String title;

    @NotBlank(message = "description is required!! ")
    @Size(min = 1, max = 100000)
    private String description;

    private int price;

    // @NotBlank(message = "stock should be either true or false")
    private boolean stock;

    private boolean live;

    private CategoryDto category;
}
