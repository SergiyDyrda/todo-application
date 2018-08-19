package com.limestone.todoboard.repository;

import com.limestone.todoboard.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository<ID> {

    Ticket save(Ticket ticker);

    // false if not found
    boolean delete(ID id);

    Optional<Ticket> get(ID id);

    List<Ticket> getAll();

    List<Ticket> getTicketsByIds(List<ID> ids);

    int deleteAll(List<ID> ticketIds);

}
