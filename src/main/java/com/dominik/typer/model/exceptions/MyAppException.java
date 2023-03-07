package com.dominik.typer.model.exceptions;

public class MyAppException extends RuntimeException {

    public MyAppException() {
    }

    public MyAppException(String message) {
        super(message);
    }

    public MyAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyAppException(Throwable cause) {
        super(cause);
    }
}
