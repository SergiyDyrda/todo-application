package com.limestone.todoboard.service;

import com.limestone.todoboard.AuthorizedUser;
import com.limestone.todoboard.domain.User;
import com.limestone.todoboard.repository.MongoUserRepository;
import com.limestone.todoboard.repository.TicketRepository;
import com.limestone.todoboard.repository.UserRepository;
import com.limestone.todoboard.util.exception.NotFoundException;
import com.limestone.todoboard.util.exception.NotFoundExceptionSupplier;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.limestone.todoboard.util.ExceptionUtil.checkNotFoundWithId;
import static com.limestone.todoboard.util.UserUtil.prepareToSave;

/**
 * Created by Sergiy Dyrda on 18.08.2018
 */

@Service
public class MongoUserService implements UserService<ObjectId>, UserDetailsService {

    private final UserRepository<ObjectId> userRepository;
    private final TicketRepository<ObjectId> ticketRepository;

    @Autowired
    public MongoUserService(MongoUserRepository userRepository, TicketRepository<ObjectId> ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(prepareToSave(user));
    }

    @Override
    public void delete(ObjectId id) throws NotFoundException {
        Assert.notNull(id, "user id must not be null");
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public User get(ObjectId id) throws NotFoundException {
        Assert.notNull(id, "user id must not be null");
        return userRepository.get(id).orElseThrow(
                NotFoundExceptionSupplier.withMessage("Not found user with id " + id));
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "Email must not be null");
        return userRepository.getByEmail(email.toLowerCase()).orElseThrow(
                NotFoundExceptionSupplier.withMessage("Not found user with email " + email));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        User oldUserRecord = get(new ObjectId(user.getId()));
        user.setTicketIds(oldUserRecord.getTicketIds());
        userRepository.save(prepareToSave(user));
    }

    @Override
    public void deleteWithTickets(User user)  throws NotFoundException {
        Assert.notNull(user, "user must not be null");
        ticketRepository.deleteAll(user.getTicketIds());
        delete(new ObjectId(user.getId()));
    }

    @Override
    public void addTicketId(Object ticketId, ObjectId userId) {
        Assert.notNull(ticketId, "ticketId must not be null");
        userRepository.addTicketId(ticketId, userId);
    }

    @Override
    public void removeTicketId(Object ticketId, ObjectId userId) {
        Assert.notNull(ticketId, "ticketId must not be null");
        userRepository.removeTicketId(ticketId, userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AuthorizedUser(getByEmail(email));
    }
}
