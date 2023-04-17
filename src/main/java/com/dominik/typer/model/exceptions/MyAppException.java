package com.dominik.typer.model.exceptions;

public class MyAppException extends RuntimeException {

    private Class<?> sourceClass;

    public MyAppException(Class<?> sourceClass) {
        this.sourceClass = sourceClass;
    }

    public MyAppException(String message, Class<?> sourceClass) {
        super(message);
        this.sourceClass = sourceClass;
    }
    public MyAppException(String message) {
        super(message);
    }

    public MyAppException(String message, Throwable cause, Class<?> sourceClass) {
        super(message, cause);
        this.sourceClass = sourceClass;
    }

    public MyAppException(Throwable cause, Class<?> sourceClass) {
        super(cause);
        this.sourceClass = sourceClass;
    }

    @Override
    public String getMessage() {
        String sourceClassName = sourceClass != null ? sourceClass.getSimpleName() : "Unknown";
        return "Exception thrown in class: " + sourceClassName + ". " + super.getMessage();
    }
}
