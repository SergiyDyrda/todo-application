package com.limestone.todoboard.util;

import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.TicketStatus;
import com.limestone.todoboard.dto.TicketTo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */
public class TicketUtil {

    public static TicketTo asTicketTo(Ticket ticket) {
        return new TicketTo(ticket.getId(),
                ticket.getName(),
                ticket.getDescription(),
                ticket.getStatus().name());
    }

    public static List<TicketTo> getListTicketTo(List<Ticket> tickets) {
        return tickets.stream().map(TicketUtil::asTicketTo).collect(Collectors.toList());
    }

    public static Ticket asTicket(TicketTo ticketTo) {
        Ticket ticket = new Ticket(ticketTo.getName(),
                ticketTo.getDescription(),
                TicketStatus.valueOf(ticketTo.getStatus()));

        String id = ticketTo.getId();
        if (id != null) ticket.setId(id);
        return ticket;
    }
}
