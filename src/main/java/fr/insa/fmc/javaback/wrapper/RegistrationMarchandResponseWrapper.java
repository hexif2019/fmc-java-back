package fr.insa.fmc.javaback.wrapper;

public class RegistrationMarchandResponseWrapper {
    private String token;
    private MarchandWrapper marchand;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public MarchandWrapper getMarchand() {
        return marchand;
    }

    public void setMarchand(MarchandWrapper marchand) {
        this.marchand = marchand;
    }
}
