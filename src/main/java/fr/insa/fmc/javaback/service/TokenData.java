package fr.insa.fmc.javaback.service;

import java.util.Date;

public class TokenData {

    public String userId;
    public Date expiration;

    public TokenData(String user,Date exp){
        userId = user;
        expiration = exp;
    }

}
