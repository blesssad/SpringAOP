package com.openschool.homework1.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityNotExistException extends RuntimeException {
    public EntityNotExistException(String message) {
        super(message);
    }

    public EntityNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotExistException(Throwable cause) {
        super(cause);
    }
}
