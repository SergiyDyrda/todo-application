package com.limestone.todoboard.util;


import com.limestone.todoboard.domain.User;
import com.limestone.todoboard.dto.UserTo;
import org.bson.types.ObjectId;

import static java.util.Objects.requireNonNull;

public class UserUtil {

    public static User asUser(UserTo newUser) {
        return new User(new ObjectId(newUser.getHexStringId()),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPassword());
    }

    public static UserTo asTo(User user) {
        return new UserTo(requireNonNull(user.getId()),
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
