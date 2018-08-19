package com.limestone.todoboard.web.handler;

import com.limestone.todoboard.util.ExceptionUtil;
import com.limestone.todoboard.util.exception.ErrorInfo;
import com.limestone.todoboard.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * Created on 07.09.2017.
 *
 * @author Sergiy Dyrda
 */

@RestControllerAdvice(annotations = {Controller.class, RestController.class})
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {

    private static Logger LOG = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    //  http://stackoverflow.com/a/22358422/548473
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo handleError(HttpServletRequest req, NotFoundException e) {
        return logAndGetErrorInfo(req, e, false);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValidationException.class)
    public ErrorInfo handleError(HttpServletRequest req, ValidationException e) {
        return logAndGetErrorInfo(req, e, false);
    }


    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true);
    }


    private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException) {
        Throwable rootCause = ExceptionUtil.getRootCause(e);
        if (logException) {
            LOG.error("Exception at request " + req.getRequestURL(), rootCause);
        } else {
            LOG.warn("Exception at request " + req.getRequestURL() + ": " + rootCause.toString());
        }

        return new ErrorInfo(req.getRequestURL(), rootCause);
    }


}
