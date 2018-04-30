package fr.insa.fmc.javaback.wrapper;

public class RegisterWrapper {
    private UserWrapper user;
    private String mdp;

    public UserWrapper getUser() {
        return user;
    }

    public void setUser(UserWrapper user) {
        this.user = user;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
