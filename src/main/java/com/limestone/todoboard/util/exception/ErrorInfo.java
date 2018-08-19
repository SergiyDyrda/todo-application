package com.limestone.todoboard.util.exception;

/**
 * Created on 07.09.2017.
 *
 * @author Sergiy Dyrda
 */
public class ErrorInfo {
    private final String url;
    private final String cause;
    private final String detail;

    public ErrorInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getLocalizedMessage();
    }
}
