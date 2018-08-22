package com.limestone.todoboard.repository;

import com.limestone.todoboard.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface MongoSpringDataUserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByEmail(String email);
}
