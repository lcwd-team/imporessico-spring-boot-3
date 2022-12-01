package com.lcwd.store.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lcwd.store.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

    // 1. custom finder methods:
    // above is implemented by spring boot data jpa
    User findByName(String name);

    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findByEmailOrName(String email, String password);

    List<User> findByNameContaining(String name);

    // 2. Query methods: we write queries
    @Query(value = "select * from jpa_users", nativeQuery = true)
    List<User> findUserByCustomQuery();

    // JPQL- JP Query Language

    // from User u
    @Query(" select u from User u where u.email =: email")
    // @Query("select u from User where u.email :=email and u.password := password")
    User findByEmailUsingJPQL(@Param("email") String userEmail);

}
