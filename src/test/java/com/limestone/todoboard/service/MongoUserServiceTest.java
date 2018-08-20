package com.limestone.todoboard.service;

import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static com.limestone.todoboard.UserTestData.petia;
import static com.limestone.todoboard.UserTestData.vasia;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MongoUserServiceTest extends AbstractServiceTest {

    @Autowired
    private MongoUserService userService;

    @Before
    public void initTestData() throws Exception {
        super.initTestData("users-testdata.json", User.class);
        super.initTestData("tickets-testdata.json", Ticket.class);
    }

    @Test
    public void save() {
        User user = new User("new user", "password", "email");
        User saved = userService.save(user);
        assertTrue(userService.getAll().contains(saved));
    }

    @Test
    public void delete() {
        userService.delete(petia.getId());
        assertEquals(Collections.singletonList(vasia), userService.getAll());
    }

    @Test
    public void get() {
        userService.getAll().stream().map(User::getId).forEach(System.out::println);
        userService.get(vasia.getId());
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteWithTickets() {
    }

    @Test
    public void addTicketId() {
    }

    @Test
    public void removeTicketId() {
    }


    @After
    public void cleanUpTestData() throws Exception {
        super.cleanUpTestData(Ticket.class);
        super.cleanUpTestData(User.class);
    }
}