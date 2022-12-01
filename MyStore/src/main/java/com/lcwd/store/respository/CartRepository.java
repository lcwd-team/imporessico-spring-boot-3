package com.lcwd.store.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.store.entities.Cart;
import com.lcwd.store.entities.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    //
    Optional<Cart> findByUser(User user);

}
