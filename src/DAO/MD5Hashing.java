package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Hashing {
    public static void main(String[] args) throws Exception {
        System.out.println(MD5Hashing.getRandomString(10));
    }

    public static String getRandomString(int length) {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 26) { // length of the random string.
            int index = (int) (rnd.nextFloat() * 26);
            salt.append(rnd.charAt(index));
        }
        String randomString = salt.toString();
        return randomString;

    }
}
