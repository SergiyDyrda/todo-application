package com.limestone.todoboard.web.controller;

import com.limestone.todoboard.AuthorizedUser;
import com.limestone.todoboard.domain.Ticket;
import com.limestone.todoboard.dto.TicketTo;
import com.limestone.todoboard.service.TicketService;
import com.limestone.todoboard.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.limestone.todoboard.domain.TicketStatus.*;
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

    private final TicketService<String> ticketService;

    @Autowired
    public TicketController(TicketService<String> ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ModelAndView getUserTickets() {
        LOGGER.info("get all tickets");
        ModelAndView mv = new ModelAndView("tickets");
        List<Ticket> userTickets = ticketService.getUserTickets(AuthorizedUser.id());
        divideTicketsByStatusAndSetToView(getListTicketTo(userTickets), mv);
        return mv;
    }

    private void divideTicketsByStatusAndSetToView(List<TicketTo> tickets, ModelAndView modelAndView) {
        List<TicketTo> todo = new ArrayList<>();
        List<TicketTo> inProgress = new ArrayList<>();
        List<TicketTo> completed = new ArrayList<>();
        final String todoStr = TODO.name();
        final String inProgressStr = IN_PROGRESS.name();
        final String completedStr = COMPLETED.name();
        tickets.forEach(t -> {
            if (t.getStatus().equals(todoStr)) {
                todo.add(t);
            } else if (t.getStatus().equals(inProgressStr)) {
                inProgress.add(t);
            } else if (t.getStatus().equals(completedStr)) {
                completed.add(t);
            }
        });

        modelAndView.addObject("todo", todo);
        modelAndView.addObject("inProgress", inProgress);
        modelAndView.addObject("completed", completed);

    }

    @GetMapping("/{ticketId}")
    public TicketTo getTicketWithId(@PathVariable("ticketId") String ticketId) {
        LOGGER.info("get ticket with id {}", ticketId);
        return asTicketTo(ticketService.get(ticketId));
    }

    @PostMapping
    public ResponseEntity<TicketTo> createOrUpdateTicket(@Valid TicketTo ticketTo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(ExceptionUtil.getErrorResponse(bindingResult));
        }
        Ticket nativeTicket = asTicket(ticketTo);
        if (nativeTicket.isNew()) {
            nativeTicket.setId(null);
            LOGGER.info("create ticket {} with status {}", ticketTo.getName(), ticketTo.getStatus());
            Ticket created = ticketService.save(nativeTicket, AuthorizedUser.id());
            if (created == null) {
                return ResponseEntity.badRequest().build();
            }
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(TICKET_URL + "/{id}")
                    .buildAndExpand(created.getId())
                    .toUri();
            return ResponseEntity.created(uriOfNewResource).body(asTicketTo(created));
        } else {
            LOGGER.info("update ticket {}", ticketTo.getId());
            ticketService.update(asTicket(ticketTo));
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity deleteTicket(@PathVariable("ticketId") String ticketId) {
        LOGGER.info("delete ticket {}", ticketId);

        ticketService.delete(ticketId, AuthorizedUser.id());

        return ResponseEntity.noContent().build();
    }

}
