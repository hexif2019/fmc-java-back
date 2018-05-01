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

    public String getMessage() {
        return message;
    }

    public String getClassName() {
        return className;
    }

    public String getPath() {
        return path;
    }

    public String getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

