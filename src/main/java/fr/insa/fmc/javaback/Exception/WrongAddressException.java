package fr.insa.fmc.javaback.Exception;

public class WrongAddressException extends RuntimeException {
    public WrongAddressException(String message) {
        super(message);
    }
}