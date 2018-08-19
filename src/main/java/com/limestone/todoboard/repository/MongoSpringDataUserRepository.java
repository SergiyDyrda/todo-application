package com.limestone.todoboard.repository;

import com.limestone.todoboard.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created on 29.08.2017.
 *
 * @author Sergiy Dyrda
 */
public interface MongoSpringDataUserRepository extends MongoRepository<User, ObjectId> {

    int deleteUserById(ObjectId id);

    Optional<User> findUserByEmail(String email);
}
