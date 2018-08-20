package com.limestone.todoboard.service;

import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.TicketStatus;
import com.limestone.todoboard.domain.User;
import com.limestone.todoboard.util.exception.NotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static com.limestone.todoboard.UserTestData.petia;

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
        Ticket newTicket = new Ticket("mock-id", "new ticket", "new ticket description", TicketStatus.TODO);
        ticketService.save(newTicket, petia.getId());
    }

    @Test
    public void save() {
        Ticket newTicket = new Ticket("new ticket", "new ticket description", TicketStatus.TODO);
        Ticket saved = ticketService.save(newTicket, petia.getId());
        System.out.println(saved);
        System.out.println(saved.getId().getClass());
        System.out.println(saved.getId());
        Ticket ticket = ticketService.get(saved.getId());
        ticketService.get("5b79c14d4488e92fc4620167");
        System.out.println(ticket);
//        List<Ticket> userTickets = ticketService.getUserTickets(petia.getId());
//        assertTrue(userTickets.contains(saved));
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
        ticketService.get("5b79c14d4488e92fc4620165");
    }

    @Test
    public void update() {
    }

    @Test
    public void getAll() {
        ticketService.getAll().stream().findFirst().ifPresent(t -> {
            System.out.println(t.getClass());
            System.out.println(t.getId());
            System.out.println(t.getId().getClass());
            ticketService.get(t.getId());
        });
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