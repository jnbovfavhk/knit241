package org.knit.solutions.Task20Classes.PaswordManager.repository;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knit.solutions.Task20Classes.PaswordManager.model.PasswordEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class InMemoryPasswordRepository {

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
        try {
            PasswordEntry newNote = new PasswordEntry(site, login, password);
            data.add(newNote);

            serializeMap();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String site) {
        for (PasswordEntry note : data) {
            if (site.equals(note.getSite())) {
                data.remove(note);
                break;
            }
        }
    }

    private void serializeMap() throws IOException {
        objectMapper.writeValue(passwordsFile, data);
    }

    private ArrayList<PasswordEntry> deserializeMap() {
        try {
            data = objectMapper.readValue(passwordsFile, new TypeReference<ArrayList<PasswordEntry>>() {
            });
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
