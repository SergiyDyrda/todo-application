package com.limestone.todoboard.repository;

import com.limestone.todoboard.domain.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created on 19.08.2018 by Sergiy Dyrda.
 */

@Repository
public class MongoUserRepository implements UserRepository<ObjectId> {

    private final MongoSpringDataUserRepository innerRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoUserRepository(MongoSpringDataUserRepository repository, MongoTemplate mongoTemplate) {
        this.innerRepository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User save(User user) {
        if (!user.isNew() && get(new ObjectId(user.getId())) == null) {
            return null;
        }
        return innerRepository.save(user);
    }

    @Override
    public boolean delete(ObjectId id) {
        return innerRepository.deleteUserById(id) != 0;
    }

    @Override
    public Optional<User> get(ObjectId id) {
        return Optional.ofNullable(
                mongoTemplate.findById(id.toString(), User.class)
        );
//        return innerRepository.findById(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return innerRepository.findUserByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return innerRepository.findAll();
    }

    @Override
    public void addTicketId(Object ticketId, ObjectId userId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(userId)),
                new Update().push("ticketIds", ticketId), User.class);
    }

    @Override
    public void removeTicketId(Object ticketId, ObjectId userId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(userId)),
                new Update().pull("ticketIds", ticketId), User.class);
    }
}
