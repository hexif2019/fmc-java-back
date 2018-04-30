package fr.insa.fmc.javaback.Exception;



public class ExceptionMessage {
    private String message;
    private String className;
    private String path;
    private String date;

    public ExceptionMessage(String message, String className, String path, String date) {
        this.message = message;
        this.className = className;
        this.path = path;
        this.date = date;
    }
}

