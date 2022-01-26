package ru.skypro.exception;

public class NullElementException extends RuntimeException {
    public NullElementException(String message) {
        super(message);
    }

    public NullElementException() {
        super("Элемент null не допускается!");
    }
}
