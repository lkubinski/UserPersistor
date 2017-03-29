package com.lukasz;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

/**
 * Created by LKubinski on 24/03/2017.
 */
public class PasswordEncryptionTest {

    @Test
    public void ensureItSavesThePasswordWithASalt(){
        final String passwordToEncrypt = "password";

        final PasswordEncryption passwordEncryption = new PasswordEncryption();

        final String encryptedWithSaltPassword = passwordEncryption.encrypt(passwordToEncrypt);

        final String encryptedPassword = encodeBase64String((passwordToEncrypt).getBytes());

        assertFalse(encryptedWithSaltPassword.equals(encryptedPassword));
    }

}