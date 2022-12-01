package com.lcwd.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private int cardItemId;

    private int quantity;

    private int totalPrice;

    private ProductDto product;

}
