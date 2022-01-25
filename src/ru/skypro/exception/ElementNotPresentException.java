package ru.skypro.exception;

public class ElementNotPresentException extends RuntimeException {
    public ElementNotPresentException(String message) {
        super(message);
    }

    public ElementNotPresentException() {
    }
}
