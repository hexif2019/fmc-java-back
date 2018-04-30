package fr.insa.fmc.javaback.wrapper;

public class RegistrationResponseWrapper {
    private String token;
    private UserWrapper user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserWrapper getUser() {
        return user;
    }

    public void setUser(UserWrapper user) {
        this.user = user;
    }
}
