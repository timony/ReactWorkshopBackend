package com.tieto.mikactom.reactworkshop.backend.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception type that signalizes that the app was not able to find an entity.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Creates a NotFoundException with an ID of not found entity.
     *
     * @param id identifier of the requested entity.
     */
    public NotFoundException(String id) {
        super(String.format("Entity with ID %s not found.", id));
    }

    public NotFoundException(Long id) {
        this(String.valueOf(id));
    }

}
