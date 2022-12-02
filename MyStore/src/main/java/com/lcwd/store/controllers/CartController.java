package com.lcwd.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.store.dtos.CartDto;
import com.lcwd.store.dtos.CartItemRequest;
import com.lcwd.store.services.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

    // add item to cart
    @Autowired
    private CartService cartService;

    String userId = "bff50774-429d-409f-8a02-63240dfd2237";

    @PostMapping("/add-item")
    public ResponseEntity<CartDto> addItemToCart(@RequestBody CartItemRequest cartItemRequest) {
        CartDto cartDto = cartService.addItemToCart(cartItemRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);

    }

    @GetMapping
    public ResponseEntity<CartDto> getCart() {
        CartDto cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);

    }

    // TODO: remove item from cart

    // TODO: Clear cart

}
