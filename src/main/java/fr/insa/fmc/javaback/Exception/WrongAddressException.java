package fr.insa.fmc.javaback.Exception;

import java.io.IOException;

public class WrongAddressException extends IOException {
    public WrongAddressException(String message) {
        super(message);
    }
}