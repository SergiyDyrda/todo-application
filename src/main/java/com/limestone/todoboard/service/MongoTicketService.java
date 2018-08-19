package com.limestone.todoboard.service;

import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.User;
import com.limestone.todoboard.repository.TicketRepository;
import com.limestone.todoboard.repository.UserRepository;
import com.limestone.todoboard.util.exception.NotFoundException;
import com.limestone.todoboard.util.exception.NotFoundExceptionSupplier;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.limestone.todoboard.util.ExceptionUtil.checkNotFoundWithId;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */

@Service
public class MongoTicketService implements TicketService<ObjectId> {

    private final TicketRepository<ObjectId> ticketRepository;
    private final UserRepository<ObjectId> userRepository;

    @Autowired
    public MongoTicketService(TicketRepository<ObjectId> ticketRepository, UserRepository<ObjectId> userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Ticket save(Ticket ticket, ObjectId userId) {
        Assert.notNull(ticket, "ticket must not be null");
        Assert.notNull(userId, "userId must not be null");
        Ticket saved = ticketRepository.save(ticket);
        userRepository.addTicketId(saved.getId(), userId);
        return saved;
    }

    @Override
    public void delete(ObjectId ticketId, ObjectId userId) throws NotFoundException {
        Assert.notNull(ticketId, "ticketId must not be null");
        Assert.notNull(ticketId, "userId must not be null");
        checkNotFoundWithId(ticketRepository.delete(ticketId), ticketId);
        checkNotFoundWithId(userRepository.get(userId), userId);
        userRepository.removeTicketId(ticketId, userId);
    }

    @Override
    public Ticket get(ObjectId id) throws NotFoundException {
        Assert.notNull(id, "objectId must not be null");
        return ticketRepository.get(id).orElseThrow(
                NotFoundExceptionSupplier.withMessage("Not found entity with id " + id));
    }

    @Override
    public void update(Ticket ticket) {
        Assert.notNull(ticket, "ticket must not be null");
        checkNotFoundWithId(ticketRepository.save(ticket), ticket.getId());
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.getAll();
    }

    @Override
    public List<Ticket> getUserTickets(ObjectId userId) {
        User user = userRepository.get(userId)
                .orElseThrow(NotFoundExceptionSupplier.withMessage(
                        "Not found user with id: " + userId.toHexString()));
        return ticketRepository.getTicketsByIds(user.getTicketIds());
    }
}
