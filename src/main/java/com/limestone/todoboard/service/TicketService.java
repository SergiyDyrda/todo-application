package com.limestone.todoboard.service;


import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.util.exception.NotFoundException;

import java.util.List;

public interface TicketService<ID> {

    Ticket save(Ticket ticket, ID userId);

    void delete(ID id, ID userId) throws NotFoundException;

    Ticket get(ID id) throws NotFoundException;

    void update(Ticket ticket);

    List<Ticket> getAll();

    List<Ticket> getUserTickets(ID userId);
}
