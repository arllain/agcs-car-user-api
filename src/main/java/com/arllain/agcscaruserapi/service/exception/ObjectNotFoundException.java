package com.arllain.agcscaruserapi.service.exception;

/**
 * @author arllain
 *
 */
public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public ObjectNotFoundException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}