package org.knit.solutions.Task20Classes.PaswordManager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class PasswordService {
    private Map<String, char[]> loginsAndPasswords;


    public void add(String login, char[] password) throws IOException {
        loginsAndPasswords.put(login, password);
        serializeMap();
    }

    public void delete(String login) throws IOException {
        loginsAndPasswords.remove(login);
        serializeMap();
    }

    private void serializeMap() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("org/knit/solutions/Task20Classes/PaswordManager/Passwords/passwords.json"), loginsAndPasswords);
    }

    private void deserializeMap() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        loginsAndPasswords = objectMapper.readValue(new File("org/knit/solutions/Task20Classes/PaswordManager/Passwords/passwords.json"), Map.class);
    }
}
