package org.knit.solutions.Task20Classes.PaswordManager.config;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knit.solutions.Task20Classes.PaswordManager.model.PasswordEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "org.knit.solutions.Task20Classes.PaswordManager")
public class AppConfig {
    Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public Cipher cipherService() {
        try {
            return Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public File passwordsFile() {
        return new File("src/main/java/org/knit/solutions/Task20Classes/PaswordManager/Passwords/passwords.json");
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public ArrayList<PasswordEntry> data() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<PasswordEntry> data = objectMapper.readValue(
                    new File("src/main/java/org/knit/solutions/Task20Classes/PaswordManager/Passwords/passwords.json"),
                    new TypeReference<ArrayList<PasswordEntry>>() {
                    });
            return data;
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Console console() {
        return System.console();
    }
}
