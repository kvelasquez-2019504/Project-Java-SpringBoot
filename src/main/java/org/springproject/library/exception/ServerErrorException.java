package org.springproject.library.exception;

public abstract class ServerErrorException extends RuntimeException {

    public ServerErrorException(String message) {
        super(message);
    }
}