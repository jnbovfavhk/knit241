package org.knit.solutions.Task20Classes.PaswordManager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Service
public class AESEncryptionService implements EncryptionService {

    @Autowired
    private Cipher cipher;
    private SecretKey key;
    private final int KEY_LENGTH = 32;
    private final Logger log = LoggerFactory.getLogger(AESEncryptionService.class);


    public SecretKey generateKeyFromPassword(char[] password) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] tmp = digest.digest(new String(password).getBytes(StandardCharsets.UTF_8));

            if (tmp.length < KEY_LENGTH) {
                log.debug("Длина сгенерированного ключа меньше нужной");

                byte[] paddedKey = new byte[KEY_LENGTH];
                System.arraycopy(tmp, 0, paddedKey, 0, tmp.length);
                tmp = paddedKey;

            } else if (tmp.length > KEY_LENGTH) {
                log.debug("Длина сгенерированного ключа больше нужной");
                byte[] trimmedKey = new byte[KEY_LENGTH];
                System.arraycopy(tmp, 0, trimmedKey, 0, KEY_LENGTH);
                tmp = trimmedKey;
            }
            log.debug("Ключ был пробразован к нужной длине");
            return new SecretKeySpec(tmp, "AES");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }


    @Override
    public char[] encrypt(char[] password) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedPassword = cipher.doFinal(new String(password).getBytes(StandardCharsets.UTF_8));

        log.debug("Пароль зашифрован: " + new String(encryptedPassword));

        return Base64.getEncoder().encodeToString(encryptedPassword).toCharArray();
    }

    @Override
    public char[] decrypt(char[] input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(new String(input)));

        log.debug("Пароль расшифрован. Его шифр:" + new String(input));

        return new String(decrypted).toCharArray();
    }


    public void setSecretKey(char[] masterPassword) {

        key = generateKeyFromPassword(masterPassword);

        log.debug("Установлен ключ для шифратора");
    }
}
