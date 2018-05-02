package fr.insa.fmc.javaback.exception;

public class WrongAddressException extends RuntimeException {
    public WrongAddressException(String message) {
        super(message);
    }
}