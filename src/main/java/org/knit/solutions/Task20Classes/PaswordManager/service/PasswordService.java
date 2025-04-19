package org.knit.solutions.Task20Classes.PaswordManager.service;

import org.knit.solutions.Task20Classes.PaswordManager.model.PasswordEntry;
import org.knit.solutions.Task20Classes.PaswordManager.repository.InMemoryPasswordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;

@Service
public class PasswordService {


    @Autowired
    InMemoryPasswordRepository passwordRepository;

    @Autowired
    AESEncryptionService encryptionService;

    @Autowired
    Logger log = LoggerFactory.getLogger(PasswordService.class);

    public void add(String site, String login, char[] password) throws IOException {
        try {
            char[] encryptedPassword = encryptionService.encrypt(password);


            if (!passwordRepository.getLoginsAndPasswords().contains(new PasswordEntry(site, "", new char[1]))) {
                passwordRepository.add(site, login, encryptedPassword);
                log.info("Добавлена запись для сайта " + site);

            } // Если уже есть запись с таким сайтом, то обновляем для него логин и пароль
            else {
                delete(site);
                add(site, login, password);
                log.info("Для сайта " + " запись была перезаписана");
            }

        } catch (IllegalBlockSizeException | InvalidKeyException | BadPaddingException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    public void delete(String site) {
        passwordRepository.delete(site);
        log.info("Запись для сайта " + site + " была удалена");
    }

    public ArrayList<PasswordEntry> getList() {
        return passwordRepository.getLoginsAndPasswords();
    }

    public char[] getPassword(String site) {
        ArrayList<PasswordEntry> data = getList();

        for (PasswordEntry passwordEntry : data) {
            if (passwordEntry.getSite().equals(site)) {
                char[] encryptedPassword = passwordEntry.getEncryptedPassword();

                try {
                    char[] password = encryptionService.decrypt(encryptedPassword);
                    return password;
                } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}
