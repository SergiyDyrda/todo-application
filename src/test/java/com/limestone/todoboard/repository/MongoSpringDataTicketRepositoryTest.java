package com.limestone.todoboard.repository;

import com.limestone.todoboard.TodoboardApplicationTests;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MongoSpringDataTicketRepositoryTest extends TodoboardApplicationTests {

    @Autowired
    private MongoSpringDataTicketRepository repository;

    @Test
    public void findTicketsByIdIn() {
        repository.findTicketsByIdIn(Arrays.asList(
           new ObjectId("5b76f84d4692873c6bef0de4"),
           new ObjectId("5b76f84d4692873c6bef0de5"),
           new ObjectId("5b76f84d4692873c6bef0de7")
        )).forEach(t -> System.out.println(t.getDescription()));
    }

    @Test
    public void deleteTicketById() {
        int res = repository.deleteTicketById(new ObjectId("5b76f84d4692873c6bef0de7"));
        assertEquals(1, res);
    }

    @Test
    public void deleteTicketsByIdIn() {
        int res = repository.deleteTicketsByIdIn(Arrays.asList(
                new ObjectId("5b76f84d4692873c6bef0de4"),
                new ObjectId("5b76f84d4692873c6bef0de5")
        ));
        assertEquals(2, res);
    }
}