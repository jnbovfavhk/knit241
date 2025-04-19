package org.knit.solutions.Task20Classes.PaswordManager.security;

import org.knit.solutions.Task20Classes.PaswordManager.service.AESEncryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.*;
import java.security.InvalidKeyException;
import java.util.Arrays;

@Service
public class MasterPasswordManager {
    private final String passFile = "src/main/java/org/knit/solutions/Task20Classes/PaswordManager/Passwords/masterPassword.txt";

    private final Logger log = LoggerFactory.getLogger(MasterPasswordManager.class);
    @Autowired
    AESEncryptionService encryptionService;


    public boolean isMasterPasswordSameToFile(char[] inputMasterPassword) {
        char[] deserializedMasterPassword = deserializeMasterPassword(inputMasterPassword);


        encryptionService.setSecretKey(inputMasterPassword);
        try {
            char[] encryptedInputMasterPassword = encryptionService.encrypt(inputMasterPassword);
            return Arrays.equals(deserializedMasterPassword, encryptedInputMasterPassword);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

    }

    private char[] deserializeMasterPassword(char[] sparePassword) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(passFile))) {
            log.debug("Мастер-пароль десериализован");
            return (char[]) ois.readObject();
        } catch (ClassNotFoundException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        } catch (IOException e) {
            // Если файл пуст, шифруем и записываем туда новый мастер-пароль
            encryptionService.setSecretKey(sparePassword);
            try {
                char[] encryptedSparePassword = encryptionService.encrypt(sparePassword);
                serializeMasterPassword(encryptedSparePassword);
                return encryptedSparePassword;

            } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
                log.error(ex.toString());
                throw new RuntimeException(ex);
            }

        }
    }

    private void serializeMasterPassword(char[] masterPassword) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(passFile))) {
            oos.writeObject(masterPassword);
            log.debug("Новый мастер-пароль сериализован");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
