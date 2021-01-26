package io.c12.bala.c12app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException implements Supplier<UserNotFoundException> {

    public UserNotFoundException() {
        super("User not found for the id");
    }

    @Override
    public UserNotFoundException get() {
        return this;
    }
}
