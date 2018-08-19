package com.limestone.todoboard.util;

import com.limestone.todoboard.util.exception.NotFoundException;
import org.springframework.validation.BindingResult;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static <ID> void checkNotFoundWithId(boolean found, ID id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T, ID> T checkNotFoundWithId(T object, ID id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static Throwable getRootCause(Throwable e) {
        Throwable result = e;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;

    }

    public static String getErrorResponse(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        result.getFieldErrors().forEach(fe -> sb.append(fe.getDefaultMessage()).append("<br>"));
        return sb.toString();
    }
}