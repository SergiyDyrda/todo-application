package com.limestone.todoboard.service;

import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.TicketStatus;
import com.limestone.todoboard.domain.User;
import com.limestone.todoboard.util.exception.NotFoundException;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static com.limestone.todoboard.UserTestData.petia;
import static org.junit.Assert.assertTrue;

public class MongoTicketServiceTest extends AbstractServiceTest {

    @Autowired
    private MongoTicketService ticketService;

    @Autowired
    private MongoUserService userService;

    @Before
    public void initTestData() throws IOException {
        super.initTestData("tickets-testdata.json", Ticket.class);
        super.initTestData("users-testdata.json", User.class);
    }

    @Test(expected = NotFoundException.class)
    public void saveNotFound() {
        Ticket newTicket = new Ticket(ObjectId.get(), "new ticket", "new ticket description", TicketStatus.TODO);
        ticketService.save(newTicket, petia.getId());
    }

    @Test
    public void save() {
        userService.getAll().forEach(System.out::println);
        System.out.println(userService.get(petia.getId()));
        Ticket newTicket = new Ticket(null, "new ticket", "new ticket description", TicketStatus.TODO);
        Ticket saved = ticketService.save(newTicket, petia.getId());
        List<Ticket> userTickets = ticketService.getUserTickets(petia.getId());
        assertTrue(userTickets.contains(saved));
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getUserTickets() {
    }

    @After
    public void cleanUpTestData() {
        super.cleanUpTestData(Ticket.class);
        super.cleanUpTestData(User.class);
    }
}