package fr.insa.fmc.javaback.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TokenGenerationService {

    private static final String algo = "SHA1PRNG";

    public static String GenerateCode() {
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance(algo);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            return "123456";
        }
        int myInt = sr.nextInt();
        int randInt = myInt / 10000;
        return String.valueOf(randInt);
    }
}
