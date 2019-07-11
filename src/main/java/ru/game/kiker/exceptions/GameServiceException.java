package ru.game.kiker.exceptions;

public class GameServiceException extends RuntimeException {
    public GameServiceException() {
        super();
    }
    public GameServiceException(String message) {
        super(message);
    }
    public GameServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
