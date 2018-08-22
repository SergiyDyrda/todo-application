package com.limestone.todoboard.service;

import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.TicketStatus;
import com.limestone.todoboard.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.limestone.todoboard.TicketTestData.*;
import static com.limestone.todoboard.UserTestData.petia;
import static com.limestone.todoboard.UserTestData.vasia;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MongoTicketServiceTest extends AbstractServiceTest {

    @Autowired
    private MongoTicketService ticketService;

    @Before
    public void initTestData() throws Exception {
        super.initTestData();
    }

    @Test
    public void name() {
        System.out.println();
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
        List<Ticket> userTickets = ticketService.getUserTickets(petia.getId());
        assertTrue(userTickets.contains(saved));
    }

    @Test
    public void delete() {
        ticketService.delete(petia_ticket_3.getId(), petia.getId());
        assertEquals(Arrays.asList(petia_ticket_1, petia_ticket_2), ticketService.getUserTickets(petia.getId()));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        ticketService.delete(petia_ticket_3.getId(), vasia.getId());
    }

    @Test
    public void get() {
        Ticket ticket_1 = ticketService.get(vasia_ticket_1.getId());
        assertEquals(vasia_ticket_1, ticket_1);

        Ticket ticket_2 = ticketService.get(petia_ticket_2.getId());
        assertEquals(petia_ticket_2, ticket_2);

    }

    @Test
    public void update() {
        Ticket ticket = ticketService.get(vasia_ticket_2.getId());
        ticket.setDescription("new updated description");
        ticketService.update(ticket);
        assertEquals(ticket, ticketService.get(vasia_ticket_2.getId()));
    }

    @Test
    public void getUserTickets() {
        assertEquals(Arrays.asList(vasia_ticket_1, vasia_ticket_2, vasia_ticket_3),
                ticketService.getUserTickets(vasia.getId()));
        assertEquals(Arrays.asList(petia_ticket_1, petia_ticket_2, petia_ticket_3),
                ticketService.getUserTickets(petia.getId()));
    }
}