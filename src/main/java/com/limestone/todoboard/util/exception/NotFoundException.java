package com.limestone.todoboard.util.exception;

/**
 * Created by SergiyDyrda on 17.08.2018
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
