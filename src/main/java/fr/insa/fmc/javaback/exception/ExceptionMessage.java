package fr.insa.fmc.javaback.exception;



public class ExceptionMessage {
    private String message;
    private String className;
    private String path;
    private String date;
    private String status;

    public ExceptionMessage(String message, String className, String path, String date, String status) {
        this.message = message;
        this.className = className;
        this.path = path;
        this.date = date;
        this.status = status;
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

    public String getStatus() {
        return status;
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

    public void setStatus(String status) {
        this.status = status;
    }
}

