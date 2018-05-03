package fr.insa.fmc.javaback.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public class GenerationService {

    private static final String algo = "SHA1PRNG";

    public static String GenerateCode() {
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance(algo);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            return "123456";
        }
        int myInt = sr.nextInt(Integer.MAX_VALUE-100001)+100000;
        return Integer.toString(myInt).substring(0, 6);
    }

    public static String GenerateToken(){
        UUID id = UUID.randomUUID();
        return id.toString().replace("-",".");
    }
    public static String GenerateId(){
        UUID id = UUID.randomUUID();
        //System.out.println("UUID generated with timestamp : "+id.timestamp());
        return id.toString().replace("-","");
    }
}
