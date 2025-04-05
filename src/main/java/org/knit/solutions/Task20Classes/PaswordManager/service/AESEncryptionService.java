package org.knit.solutions.Task20Classes.PaswordManager.service;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class AESEncryptionService {
    private final Cipher cipher;
    SecretKeySpec key;

    public AESEncryptionService(byte[] masterPassword) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this.cipher = Cipher.getInstance("AES");
        key = new SecretKeySpec(masterPassword, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

    }

    public byte[] encrypt(char[] password) throws IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedPassword = cipher.doFinal(new String(password).getBytes());
        return encryptedPassword;
    }
}
