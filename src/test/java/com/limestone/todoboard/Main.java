package com.limestone.todoboard;

import com.limestone.todoboard.util.PasswordUtil;

/**
 * Created by Sergiy Dyrda on 19.08.2018
 */
public class Main {

    public static void main(String[] args) {
        String petiaPassword = PasswordUtil.PASSWORD_ENCODER.encode("petia-password");
        String vasiaPassword = PasswordUtil.PASSWORD_ENCODER.encode("vasia-password");
        System.out.println(petiaPassword);
        System.out.println(vasiaPassword);
    }
}
