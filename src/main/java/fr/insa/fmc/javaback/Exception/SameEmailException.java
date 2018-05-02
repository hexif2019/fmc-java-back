package fr.insa.fmc.javaback.Exception;

public class SameEmailException extends RuntimeException{
    public SameEmailException(String message) {
        super(message);
    }
}
