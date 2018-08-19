package com.limestone.todoboard.util;

import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.domain.TicketStatus;
import com.limestone.todoboard.dto.TicketTo;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */
public class TicketUtil {

    public static TicketTo asTicketTo(Ticket ticket) {
        return new TicketTo(ticket.getId().toHexString(),
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

        String id = ticketTo.getHexStringId();
        if (id != null) ticket.setId(new ObjectId(id));
        return ticket;
    }

    public static String idToHexString(ObjectId id) {
        return id.toHexString();
    }

    public static ObjectId idFromHexString(String hexStringId) {
        return new ObjectId(hexStringId);
    }

    public static List<String> idsToHexString(List<ObjectId> ids) {
        return ids.stream().map(ObjectId::toHexString).collect(Collectors.toList());
    }

    public static List<ObjectId> idsFromHexString(List<String> ids) {
        return ids.stream().map(ObjectId::new).collect(Collectors.toList());
    }
}
