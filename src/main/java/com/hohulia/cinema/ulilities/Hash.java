package com.hohulia.cinema.ulilities;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    private final static String SALT = "some_random_salt";
    public static String hashPassword(String password) {
        String result = null;
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] hash1 = md.digest(password.getBytes());

            md.reset();
            byte[] hash2 = md.digest(hash1);

            md.reset();
            md.update(SALT.getBytes());
            md.update(hash2);

            byte[] digest = md.digest();
            for (int i = 0; i < digest.length; i++) {
                digest[i] = (byte) (digest[i] ^ hash1[i]);
            }

            result = String.format("%0" + (digest.length << 1) + "x",new BigInteger(1,digest));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }
}
