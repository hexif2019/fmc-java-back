package fr.insa.fmc.javaback.service;


import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {


    private Map<String,TokenData> tokenValidity = new HashMap<>();

    public Map<String, TokenData> getTokenValidity() {
        return tokenValidity;
    }

    public void setTokenValidity(Map<String, TokenData> tokenValidity) {
        this.tokenValidity = tokenValidity;
    }

    public boolean tryStoreToken(String token,String userId){
        TokenData data = new TokenData(userId,new Date());
        return tokenValidity.putIfAbsent(token, data) == null;
    }
    public boolean getValidity(String token){
        Date date = new Date();
        return tokenValidity.containsKey(token) && tokenValidity.get(token).expiration.getTime() > date.getTime();
    }

}
