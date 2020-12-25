package com.arllain.agcscaruserapi.service.exception;

/**
 * @author arllain
 */
public class AuthenticationCustomException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public AuthenticationCustomException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public AuthenticationCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
