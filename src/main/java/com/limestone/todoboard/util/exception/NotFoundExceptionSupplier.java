package com.limestone.todoboard.util.exception;

import java.util.function.Supplier;

/**
 * Created by SergiyDyrda on 17.08.2018
 */
public class NotFoundExceptionSupplier implements Supplier<NotFoundException> {

    private final NotFoundException exception;

    private NotFoundExceptionSupplier(String message) {
        this.exception = new NotFoundException(message);
    }

    public static NotFoundExceptionSupplier withMessage(String message) {
        return new NotFoundExceptionSupplier(message);
    }

    @Override
    public NotFoundException get() {
        return exception;
    }
}
