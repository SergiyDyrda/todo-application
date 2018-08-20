package com.limestone.todoboard.repository;


import com.limestone.todoboard.domain.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoSpringDataTicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findTicketsByIdIn(List<String> ids);

    int deleteTicketsByIdIn(List<String> ids);
}
