package com.limestone.todoboard.repository;

import com.limestone.todoboard.domain.Ticket;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */

@Repository
public class MongoTicketRepository implements TicketRepository<ObjectId> {

    private final MongoSpringDataTicketRepository innerRepository;

    @Autowired
    public MongoTicketRepository(MongoSpringDataTicketRepository repository) {
        this.innerRepository = repository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        if (!ticket.isNew() && get(ticket.getId()) == null) {
            return null;
        }
        return innerRepository.save(ticket);
    }

    @Override
    public boolean delete(ObjectId id) {
        return innerRepository.deleteTicketById(id) != 0;
    }

    @Override
    public Optional<Ticket> get(ObjectId id) {
        return innerRepository.findById(id);
    }

    @Override
    public List<Ticket> getAll() {
        return innerRepository.findAll();
    }

    @Override
    public List<Ticket> getTicketsByIds(List<ObjectId> objectIds) {
        return innerRepository.findTicketsByIdIn(objectIds);
    }

    @Override
    public int deleteAll(List<ObjectId> ticketIds) {
        return innerRepository.deleteTicketsByIdIn(ticketIds);
    }

}
