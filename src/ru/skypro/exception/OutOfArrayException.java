package ru.skypro.exception;

public class OutOfArrayException extends RuntimeException {
    public OutOfArrayException(String message) {
        super(message);
    }

    public OutOfArrayException() {
    }
}