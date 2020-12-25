package com.arllain.agcscaruserapi.controller.exception;

import java.io.Serializable;

/**
 * @author arllain
 */
public class AuthorizationException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public AuthorizationException(String message) {

        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

}
