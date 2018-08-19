package com.limestone.todoboard;

import com.limestone.todoboard.domain.Role;
import com.limestone.todoboard.domain.User;

import java.util.Arrays;
import java.util.EnumSet;

import static com.limestone.todoboard.util.TicketUtil.idFromHexString;
import static com.limestone.todoboard.util.TicketUtil.idsFromHexString;

/**
 * Created by Sergiy Dyrda on 19.08.2018
 */
public class UserTestData {

    private UserTestData() {
    }

    public static User petia = new User(
            idFromHexString("5b79c9d64488e92a7cc3c76d"),
            "Petia Piatochkin",
            "petya@mail.com",
            "petia-password",
            idsFromHexString(Arrays.asList(
                    "5b79c14d4488e92fc4620165",
                    "5b79c14d4488e92fc4620166",
                    "5b79c14d4488e92fc4620167"
            )),
            EnumSet.of(Role.ROLE_USER)
    );

    public static User vasia = new User(
            idFromHexString("5b79c9d64488e92a7cc3c76e"),
            "Vasia Pupkin",
            "vasia@mail.com",
            "vasia-password",
            idsFromHexString(Arrays.asList(
                    "5b79c14d4488e92fc4620168",
                    "5b79c14d4488e92fc4620169"
            )),
            EnumSet.of(Role.ROLE_USER)
    );
}
