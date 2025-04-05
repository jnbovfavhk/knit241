package org.knit.solutions.Task20Classes.PaswordManager.model;

import org.springframework.stereotype.Component;

@Component
public class PasswordEntry {
    private String site;
    private String login;
    private char[] encryptedPassword;


}
