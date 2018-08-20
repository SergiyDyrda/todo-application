package com.limestone.todoboard.web.controller;

import com.limestone.todoboard.AuthorizedUser;
import com.limestone.todoboard.dto.UserTo;
import com.limestone.todoboard.service.UserService;
import com.limestone.todoboard.util.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.limestone.todoboard.util.UserUtil.asUser;
import static com.limestone.todoboard.web.controller.UserController.USER_URL;

/**
 * Created by Sergiy Dyrda on 18.08.2018
 */

@RestController
@RequestMapping(USER_URL)
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    public static final String USER_URL = "/user";

    private final UserService<String> userService;

    @Autowired
    public UserController(UserService<String> userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity updateAccount(@Valid @RequestBody UserTo userTo) {
        LOGGER.info("update account: {}", userTo.getId());
        userService.update(asUser(userTo));
        AuthorizedUser.get().update(userTo);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteAccount(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("delete account: {}", AuthorizedUser.id());
        userService.deleteWithTickets(asUser(AuthorizedUser.get().getUserTo()));
        AuthenticationUtil.manualLogout(request, response);

        //TODO redirect to login
        return ResponseEntity.noContent().build();
    }
}
