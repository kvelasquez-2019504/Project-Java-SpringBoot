package org.springproject.library.exception;

public class InvalidCredentialsException extends ServerErrorException {

    public InvalidCredentialsException() {
        super("invalid username or password");
    }
}