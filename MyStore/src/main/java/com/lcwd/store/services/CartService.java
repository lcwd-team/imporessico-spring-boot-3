package com.lcwd.store.services;

import com.lcwd.store.dtos.CartDto;
import com.lcwd.store.dtos.CartItemRequest;

public interface CartService {

    // add item to cart

    CartDto addItemToCart(CartItemRequest cartItemRequest, String userId);

    // remove item to cart
    void removeItemFromCart(int itemId, String userId);

    // get cart of user
    CartDto getCart(String userId);

    //
    CartDto getCartByUserEmail(String email);

}
