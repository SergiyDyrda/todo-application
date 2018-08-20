package com.limestone.todoboard;

import com.limestone.todoboard.domain.Role;
import com.limestone.todoboard.domain.User;

import java.util.Arrays;
import java.util.EnumSet;


/**
 * Created by Sergiy Dyrda on 19.08.2018
 */
public class UserTestData {

    private UserTestData() {
    }

    public static User petia = new User(
            "5b79c9d64488e92a7cc3c76d",
            "Petia Piatochkin",
            "petya@mail.com",
            "petia-password",
            Arrays.asList(
                    "5b79c14d4488e92fc4620165",
                    "5b79c14d4488e92fc4620166",
                    "5b79c14d4488e92fc4620167"
            ),
            EnumSet.of(Role.ROLE_USER)
    );

    public static User vasia = new User(
            "5b79c9d64488e92a7cc3c76e",
            "Vasia Pupkin",
            "vasia@mail.com",
            "vasia-password",
            Arrays.asList(
                    "5b79c14d4488e92fc4620168",
                    "5b79c14d4488e92fc4620169"
            ),
            EnumSet.of(Role.ROLE_USER)
    );
}
