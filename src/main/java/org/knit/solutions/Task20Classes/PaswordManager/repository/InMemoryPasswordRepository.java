package org.knit.solutions.Task20Classes.PaswordManager.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knit.solutions.Task20Classes.PaswordManager.model.PasswordEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


@Repository
public class InMemoryPasswordRepository {
    private final Logger log = LoggerFactory.getLogger(InMemoryPasswordRepository.class);

    @Autowired
    File passwordsFile;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ArrayList<PasswordEntry> data;


    public ArrayList<PasswordEntry> getLoginsAndPasswords() {
        return data;
    }


    public void add(String site, String login, char[] password) {
        PasswordEntry newNote = new PasswordEntry(site, login, password);
        data.add(newNote);

        serializeData();
        log.info("Новая запись в данных: " + newNote);
    }

    public void delete(String site) {
        for (PasswordEntry note : data) {
            if (site.equals(note.getSite())) {
                data.remove(note);
                log.info("Из данных удалена запись " + note);
                break;
            }
        }
    }

    private void serializeData() {
        try {
            objectMapper.writeValue(passwordsFile, data);
            log.debug("Данные сериализованы");
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }
}
