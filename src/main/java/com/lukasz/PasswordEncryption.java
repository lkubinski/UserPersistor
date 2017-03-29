package com.lukasz;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;

/**
 * Created by LKubinski on 23/03/2017.
 */
public class PasswordEncryption {

    private final String SALT_PASSWORD = "Stronger password";

    public String encrypt(String password) {
        return encodeBase64String((password+SALT_PASSWORD).getBytes());

    }
}
