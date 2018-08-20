package com.limestone.todoboard.repository;

import com.limestone.todoboard.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */

public interface UserRepository<ID> {

    User save(User user);

    // false if not found
    boolean delete(ID id);

    Optional<User> get(ID id);

    Optional<User> getByEmail(String email);

    List<User> getAll();

    boolean exists(ID userId);

    void addTicketId(Object ticketId, ID userId);

    void removeTicketId(Object ticketId, ID userId);
}
