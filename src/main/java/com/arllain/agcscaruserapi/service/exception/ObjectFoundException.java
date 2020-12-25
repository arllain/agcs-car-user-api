package com.arllain.agcscaruserapi.service.exception;

/**
 * @author arllain
 *
 */
public class ObjectFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public ObjectFoundException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ObjectFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}