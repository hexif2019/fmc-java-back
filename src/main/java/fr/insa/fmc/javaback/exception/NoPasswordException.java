package fr.insa.fmc.javaback.exception;

import java.io.IOException;

public class NoPasswordException extends IOException{
    public NoPasswordException(String message) {
        super(message);
    }
}
