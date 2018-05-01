package fr.insa.fmc.javaback.wrapper;

import fr.insa.fmc.javaback.entity.Coursier;

public class AuthentificationCoursierResponseWrapper {
    private String token;
    private LivreurWrapper user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LivreurWrapper getUser() {
        return user;
    }

    public void setUser(LivreurWrapper user) {
        this.user = user;
    }
}
