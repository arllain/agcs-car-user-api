package com.arllain.agcscaruserapi.controller.exception;

import javax.servlet.http.HttpServletRequest;

import com.arllain.agcscaruserapi.service.exception.AuthenticationCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.arllain.agcscaruserapi.service.exception.ObjectFoundException;
import com.arllain.agcscaruserapi.service.exception.ObjectNotFoundException;

/**
 * @author arllain
 *
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    /**
     * @param objectNotFoundException
     * @param request
     * @return
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNotFoundException,
                                                        HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), objectNotFoundException.getMessage(),
                System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    /**
     * @param objectFoundException
     * @param request
     * @return
     */
    @ExceptionHandler(ObjectFoundException.class)
    public ResponseEntity<StandardError> objectFound(ObjectFoundException objectFoundException,
                                                     HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.CONFLICT.value(), objectFoundException.getMessage(),
                System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    /**
     * @param methodArgumentNotValidException
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException methodArgumentNotValidException,
                                                    HttpServletRequest request) {
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation error",
                System.currentTimeMillis());

        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    /**
     * @param authenticationCustomException
     * @param request
     * @return
     */
    @ExceptionHandler(AuthenticationCustomException.class)
    public ResponseEntity<StandardError> authenticationException(
            AuthenticationCustomException authenticationCustomException, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), authenticationCustomException.getMessage(),
                System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
}
