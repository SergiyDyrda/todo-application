package com.limestone.todoboard.web.controller;

import com.limestone.todoboard.AuthorizedUser;
import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.dto.TicketTo;
import com.limestone.todoboard.service.MongoTicketService;
import com.limestone.todoboard.service.MongoUserService;
import com.limestone.todoboard.service.TicketService;
import com.limestone.todoboard.service.UserService;
import com.limestone.todoboard.util.TicketUtil;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.limestone.todoboard.util.TicketUtil.*;
import static com.limestone.todoboard.web.controller.TicketController.TICKET_URL;

/**
 * Created by Sergiy Dyrda on 17.08.2018
 */
@RestController
@RequestMapping(TICKET_URL)
public class TicketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    public static final String TICKET_URL = "/tickets";

    private final TicketService<ObjectId> ticketService;
    private final UserService<ObjectId> userService;

    @Autowired
    public TicketController(MongoTicketService ticketService, MongoUserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping
    public List<TicketTo> getUserTickets() {
        LOGGER.info("get all tickets");
        ObjectId userId = idFromHexString(AuthorizedUser.id());
        List<Ticket> userTickets = ticketService.getUserTickets(userId);
        return TicketUtil.getListTicketTo(userTickets);
    }

    @GetMapping("/{hexStringId}")
    public TicketTo getTicketWithId(@PathVariable("hexStringId") String hexStringId) {
        LOGGER.info("get ticket with id {}", hexStringId);
        return asTicketTo(ticketService.get(idFromHexString(hexStringId)));
    }

    @PostMapping
    public ResponseEntity<TicketTo> createTicket(@RequestBody TicketTo ticketTo) {
        LOGGER.info("create ticket {} with status {}", ticketTo.getName(), ticketTo.getStatus());

        ObjectId userId = idFromHexString(AuthorizedUser.id());
        Ticket created = ticketService.save(asTicket(ticketTo), userId);

        if (created == null) {
            return ResponseEntity.badRequest().build();
        }

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(TICKET_URL + "/{id}")
                .buildAndExpand(created.getId().toHexString())
                .toUri();

        return ResponseEntity.created(uriOfNewResource).body(asTicketTo(created));
    }


    @PutMapping
    public ResponseEntity<TicketTo> updateTicket(@RequestBody TicketTo ticketTo) {
        LOGGER.info("update ticket {}", ticketTo.getHexStringId());
        ticketService.update(asTicket(ticketTo));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{hexStringId}")
    public ResponseEntity deleteTicket(@PathVariable("hexStringId") String hexStringId) {
        LOGGER.info("delete ticket {}", hexStringId);

        ObjectId userId = idFromHexString(AuthorizedUser.id());
        ObjectId ticketId = idFromHexString(hexStringId);
        ticketService.delete(ticketId, userId);

        return ResponseEntity.noContent().build();
    }

}
