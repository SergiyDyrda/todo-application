package com.limestone.todoboard.service;

import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.TicketStatus;
import com.limestone.todoboard.domain.User;
import com.limestone.todoboard.util.exception.NotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;

import static com.limestone.todoboard.TicketTestData.*;
import static com.limestone.todoboard.UserTestData.petia;
import static com.limestone.todoboard.UserTestData.vasia;
import static org.junit.Assert.*;

public class MongoUserServiceTest extends AbstractServiceTest {

    @Autowired
    private MongoUserService userService;

    @Autowired
    private MongoTicketService ticketService;

    @Before
    public void initTestData() throws Exception {
        super.initTestData("users-testdata.json", User.class);
        super.initTestData("tickets-testdata.json", Ticket.class);
    }

    @Test
    public void save() {
        User user = new User("new user", "password", "email");
        User saved = userService.save(user);
        assertNotNull(saved);
        assertEquals(user, saved);
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
        assertEquals(petia, userService.getByEmail(petia.getEmail()));
        assertEquals(vasia, userService.getByEmail(vasia.getEmail()));
    }

    @Test
    public void getAll() {
        assertEquals(Arrays.asList(petia, vasia), userService.getAll());
    }

    @Test
    public void update() {
        User copyVasia = userService.get(vasia.getId());
        copyVasia.setName("new Name");
        copyVasia.setEmail("updatedEmail");
        userService.update(copyVasia);
//        assertTrue(USER_MODEL_MATCHER.match(copyVasia, userService.get(vasia.getId())));
        User newVasia = userService.get(vasia.getId());
        assertEquals(copyVasia, newVasia);
    }

    @Test(expected = NotFoundException.class)
    public void deleteWithTickets() {
        userService.deleteWithTickets(vasia.getId());
        assertEquals(Collections.emptyList(), ticketService.getUserTickets(vasia.getId()));
        userService.get(vasia.getId());
    }

    @Test
    public void addTicketId() {
        Ticket newTicket = new Ticket("new ticket", "this is new ticket", TicketStatus.IN_PROGRESS);
        Ticket saved = ticketService.save(newTicket, petia.getId());
        userService.addTicketId(saved.getId(), petia.getId());
        assertEquals(
                Arrays.asList(petia_ticket_1, petia_ticket_2, petia_ticket_3, saved),
                ticketService.getUserTickets(petia.getId()));
    }

    @Test
    public void removeTicketId() {
        userService.removeTicketId(vasia_ticket_2.getId(), vasia.getId());
        assertEquals(Collections.singletonList(vasia_ticket_1), ticketService.getUserTickets(vasia.getId()));
    }


    @After
    public void cleanUpTestData() throws Exception {
        super.cleanUpTestData(Ticket.class);
        super.cleanUpTestData(User.class);
    }
}