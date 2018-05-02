package fr.insa.fmc.javaback.exception;

public class SameEmailException extends Exception {
    public SameEmailException(String message) {
        super(message);
    }
}
