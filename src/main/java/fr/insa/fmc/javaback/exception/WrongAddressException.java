package fr.insa.fmc.javaback.exception;

import java.io.IOException;

public class WrongAddressException extends IOException {
    public WrongAddressException(String message) {
        super(message);
    }
}