package com.arllain.agcscaruserapi.controller.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arllain
 *
 */
public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    /**
     * @param status
     * @param message
     * @param timeStamp
     */
    public ValidationError(Integer status, String message, Long timeStamp) {
        super(status, message, timeStamp);
    }

    /**
     * @return
     */
    public List<FieldMessage> getErrors() {
        return errors;
    }

    /**
     * @param fieldName
     * @param message
     */
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }

}
