package fr.insa.fmc.javaback.Exception;

import java.io.IOException;

public class SameEmailException extends IOException {
    public SameEmailException(String message) {
        super(message);
    }
}
