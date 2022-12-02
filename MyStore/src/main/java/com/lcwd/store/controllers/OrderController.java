package com.lcwd.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.store.dtos.CreateOrderRequest;
import com.lcwd.store.dtos.OrderDto;
import com.lcwd.store.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private String userId = "be5c94c1-cf87-4709-a07d-9f011a8afb41";

    // create
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(
            @RequestBody CreateOrderRequest createOrderData) {

        OrderDto createOrder = orderService.createOrder(createOrderData, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrder);

    }

}
