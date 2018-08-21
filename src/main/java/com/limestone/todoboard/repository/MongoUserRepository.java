package com.limestone.todoboard.repository;

import com.limestone.todoboard.domain.User;
import com.mongodb.MongoClient;
import com.mongodb.client.result.UpdateResult;
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
public class MongoUserRepository implements UserRepository<String> {

    private final MongoSpringDataUserRepository innerRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    private MongoClient client;

    @Autowired
    public MongoUserRepository(MongoSpringDataUserRepository repository, MongoTemplate mongoTemplate) {
        this.innerRepository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User save(User user) {
        if (!user.isNew() && !exists(user.getId())) {
            return null;
        }
        return innerRepository.save(user);
    }

    @Override
    public boolean delete(String id) {
        if (!exists(id)) return false;
        innerRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<User> get(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        User user = mongoTemplate.findOne(query, User.class, "users");
        return Optional.ofNullable(user);
//        return innerRepository.findById(id);
//        MongoDatabase todoboard = client.getDatabase("todoboard");
//        MongoCollection<Document> users = todoboard.getCollection("users");
//        Document document = users.find(eq("_id", id)).first();
//        if (document != null) {
//            User user = new User();
//            user.setId(document.getString("_id"));
//            user.setName(document.getString("name"));
//            user.setEmail(document.getString("email"));
//            return Optional.of(user);
//        }  else {
//            return Optional.empty();
//        }
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
    public boolean exists(String userId) {
        return innerRepository.existsById(userId);
    }

    @Override
    public boolean addTicketId(Object ticketId, String userId) {
        UpdateResult updateResult = mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(userId)),
                new Update().push("ticketIds", ticketId), User.class);
        return updateResult.getModifiedCount() != 0;
    }

    @Override
    public boolean removeTicketId(Object ticketId, String userId) {
        UpdateResult updateResult = mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(userId)),
                new Update().pull("ticketIds", ticketId), User.class);
        return updateResult.getModifiedCount() != 0;
    }
}
