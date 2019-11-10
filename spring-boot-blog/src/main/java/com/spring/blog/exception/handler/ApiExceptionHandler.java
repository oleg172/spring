package com.spring.blog.exception.handler;

import com.spring.blog.exception.handler.model.RootCause;
import com.spring.blog.exception.handler.model.error.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles org.springframework.dao.DataIntegrityViolationException. Thrown when constraint failed
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            DataIntegrityViolationException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage("Constraint error");
        apiError.setDebugMessage(ex.getMostSpecificCause().getMessage());
        apiError.setRootCause(setRootCause(apiError.getDebugMessage()));
        return buildResponseEntity(apiError);
    }

    private RootCause setRootCause(String message) {
        Pattern pattern = Pattern.compile("Ключ \".*\"");
        Matcher matcher = pattern.matcher(message);
        matcher.find();
        String rootCause = matcher.group(0);
        if (rootCause.contains("(name)")) {
            return RootCause.NAME_CONSTRAINT;
        } else if (rootCause.contains(("email"))) {
            return RootCause.EMAIL_CONSTRAINT;
        }
        return RootCause.UNKNOWN_CONSTRAINT;
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
