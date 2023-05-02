package com.talataa.androidexam.utils;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordEncrypter {


    public String encryptPassword(String password) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");


            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);


            byte[] hashedBytes = digest.digest(passwordBytes);


            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }
            String hashedPassword = hexStringBuilder.toString();

            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("Failed to encrypt password: " + e.getMessage(), e);
        }
    }

}

