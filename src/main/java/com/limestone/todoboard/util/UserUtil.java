package com.limestone.todoboard.util;


import com.limestone.todoboard.domain.User;
import com.limestone.todoboard.dto.UserTo;

public class UserUtil {

    public static User asUser(UserTo newUser) {
        return new User(newUser.getId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPassword());
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword());
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
