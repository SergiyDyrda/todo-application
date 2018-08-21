package com.limestone.todoboard.web.controller;


import com.limestone.todoboard.domain.User;
import com.limestone.todoboard.dto.UserTo;
import com.limestone.todoboard.service.UserService;
import com.limestone.todoboard.util.AuthenticationUtil;
import com.limestone.todoboard.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.limestone.todoboard.util.UserUtil.asUser;


@Controller
public class RootController {

    private final UserService<String> userService;

    @Autowired
    public RootController(UserService<String> userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({"/register"})
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userTo", new UserTo());
        modelAndView.setViewName("registration/registration");
        return modelAndView;
    }

    @PostMapping({"/register"})
    public ModelAndView registerNewUser(@Valid UserTo userTo, BindingResult bindingResult) {
        checkAlreadyExists(userTo, bindingResult);
        return createNewUser(userTo, bindingResult, "registration/successful", "registration/registration");

    }

    @GetMapping({"/access-denied"})
    public String accessDenied() {
        return "access-denied";
    }

    private void checkAlreadyExists(@Valid UserTo userTo, BindingResult bindingResult) {
        try {
            userService.getByEmail(userTo.getEmail());
            bindingResult
                    .rejectValue("email", "error.user",
                            "*User with this e-mail already registered");

        } catch (NotFoundException ignored) {
        }
    }

    private ModelAndView createNewUser(UserTo userTo, BindingResult bindingResult, String successView, String failureView) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(failureView);
            return modelAndView;
        }
        User saved = userService.save(asUser(userTo));
        AuthenticationUtil.manualLoginNewUser(saved);

        modelAndView.setViewName(successView);
        return modelAndView;
    }
}
