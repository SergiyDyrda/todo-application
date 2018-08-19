package com.limestone.todoboard.service;


import com.limestone.todoboard.domain.User;
import com.limestone.todoboard.util.exception.NotFoundException;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService<ID> {

    User save(User user);

    void delete(ID id) throws NotFoundException;

    User get(ID id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user);

    void deleteWithTickets(User user) throws NotFoundException;

    void addTicketId(Object ticketId, ID userId);

    void removeTicketId(Object ticketId, ObjectId userId);
}
