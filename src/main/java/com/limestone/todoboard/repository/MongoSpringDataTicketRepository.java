package com.limestone.todoboard.repository;


import com.limestone.todoboard.domain.Ticket;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoSpringDataTicketRepository extends MongoRepository<Ticket, ObjectId> {

    int deleteTicketById(ObjectId id);

    List<Ticket> findTicketsByIdIn(List<ObjectId> ids);

    int deleteTicketsByIdIn(List<ObjectId> ids);
}
