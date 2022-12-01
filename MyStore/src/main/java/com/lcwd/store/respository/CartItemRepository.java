package com.lcwd.store.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.store.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
