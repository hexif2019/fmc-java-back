package fr.insa.fmc.javaback.wrapper;

public class RegisterMarchandWrapper {
    private MarchandWrapper marchand;
    private String password;

    public MarchandWrapper getMarchand() {
        return marchand;
    }

    public void setMarchand(MarchandWrapper marchand) {
        this.marchand = marchand;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
