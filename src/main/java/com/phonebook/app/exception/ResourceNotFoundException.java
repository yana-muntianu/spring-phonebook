package com.phonebook.app.exception;

import java.io.Serial;

public class ResourceNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object resourceId) {
        super(resourceId != null ? resourceId.toString() : null);
    }
}
