package com.limestone.todoboard;

import com.limestone.todoboard.domain.Role;
import com.limestone.todoboard.domain.User;
import com.limestone.todoboard.util.PasswordUtil;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;


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
            PasswordUtil.encode("petia-password"),
            Arrays.asList(
                    "5b79c14d4488e92fc4620174",
                    "5b79c14d4488e92fc4620167",
                    "5b79c14d4488e92fc462016a"

            ),
            EnumSet.of(Role.ROLE_USER)
    );

    public static User vasia = new User(
            "5b79c9d64488e92a7cc3c76e",
            "Vasia Pupkin",
            "vasia@mail.com",
            PasswordUtil.encode("vasia-password"),
            Arrays.asList(
                    "5b79c14d4488e92fc4620177",
                    "5b79c14d4488e92fc4620178",
                    "5b79c14d4488e92fc4620179"
            ),
            EnumSet.of(Role.ROLE_USER)
    );

    public static ModelMatcher<User> USER_MODEL_MATCHER = (u1, u2) ->
            Objects.equals(u1.getId(), u2.getId()) &&
                    Objects.equals(u1.getName(), u2.getName()) &&
                    PasswordUtil.comparePassword(u1.getPassword(), u2.getPassword()) &&
                    Objects.equals(u1.getTicketIds(), u2.getTicketIds()) &&
                    Objects.equals(u1.getRoles(), u2.getRoles());
}
