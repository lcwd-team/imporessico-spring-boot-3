package com.lcwd.store.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.store.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
