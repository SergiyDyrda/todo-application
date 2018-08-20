package com.limestone.todoboard.repository;

import com.limestone.todoboard.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */

@Repository
public class MongoTicketRepository implements TicketRepository<String> {

    private final MongoSpringDataTicketRepository innerRepository;

    @Autowired
    public MongoTicketRepository(MongoSpringDataTicketRepository repository) {
        this.innerRepository = repository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        if (!ticket.isNew() && !innerRepository.existsById(ticket.getId())) {
            return null;
        }
        return innerRepository.save(ticket);
    }

    @Override
    public boolean delete(String id) {
        if (!innerRepository.existsById(id)) return false;
        innerRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Ticket> get(String id) {
        return innerRepository.findById(id);
    }

    @Override
    public List<Ticket> getAll() {
        return innerRepository.findAll();
    }

    @Override
    public List<Ticket> getTicketsByIds(List<String> ticketIds) {
        return innerRepository.findTicketsByIdIn(ticketIds);
    }

    @Override
    public int deleteAll(List<String> ticketIds) {
        return innerRepository.deleteTicketsByIdIn(ticketIds);
    }

}
