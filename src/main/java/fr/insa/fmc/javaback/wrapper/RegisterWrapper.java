package fr.insa.fmc.javaback.wrapper;

public class RegisterWrapper {
    private UserWrapper user;
    private String password;

    public UserWrapper getUser() {
        return user;
    }

    public void setUser(UserWrapper user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
