package org.knit.solutions.Task20Classes.PaswordManager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knit.solutions.Task20Classes.PaswordManager.model.PasswordEntry;
import org.knit.solutions.Task20Classes.PaswordManager.repository.InMemoryPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Map;

@Service
public class PasswordService {


    @Autowired
    InMemoryPasswordRepository passwordRepository;

    @Autowired
    AESEncryptionService encryptionService;

    public void add(String site, String login, char[] password) throws IOException {
        try {
            char[] encryptedPassword = encryptionService.encrypt(password);


            if (!passwordRepository.getLoginsAndPasswords().contains(new PasswordEntry(site, "", new char[1]))) {
                passwordRepository.add(site, login, encryptedPassword);


            } // Если уже есть запись с таким сайтом, то обновляем для него логин и пароль
            else {
                delete(site);
                add(site, login, password);
            }

        } catch (IllegalBlockSizeException | InvalidKeyException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String site) throws IOException {
        passwordRepository.delete(site);
    }

    public ArrayList<PasswordEntry> getList() {
        return passwordRepository.getLoginsAndPasswords();
    }
}
