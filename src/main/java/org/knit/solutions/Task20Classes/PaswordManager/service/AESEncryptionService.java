package org.knit.solutions.Task20Classes.PaswordManager.service;

import org.knit.solutions.Task20Classes.PaswordManager.security.MasterPasswordHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;


@Service
public class AESEncryptionService implements EncryptionService {

    @Autowired
    private Cipher cipher;
    private SecretKey key;
    private static final String SECRET_KEY_ALGO = "PBKDF2WithHmacSHA256";
    private static final byte[] SALT = "my-salt-value".getBytes();



    private SecretKey generateKeyFromPassword(char[] password) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_ALGO);
            KeySpec spec = new PBEKeySpec(password, SALT, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            return new SecretKeySpec(tmp.getEncoded(), "AES");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка генерации ключа", e);
        }
    }


    @Override
    public char[] encrypt(char[] password) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedPassword = cipher.doFinal(new String(password).getBytes());
        return new String(encryptedPassword).toCharArray();
    }

    @Override
    public char[] decrypt(char[] input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Arrays.toString(input).getBytes());
        return new String(decrypted).toCharArray();
    }

    public void setSecretKey(char[] masterPassword) {
        key = generateKeyFromPassword(masterPassword);
    }
}
