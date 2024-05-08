package com.openschool.homework1.entity;

import com.openschool.homework1.annotation.Throw;
import lombok.NoArgsConstructor;

@Throw
@NoArgsConstructor
public class BookException extends RuntimeException{
    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookException(Throwable cause) {
        super(cause);
    }
}
