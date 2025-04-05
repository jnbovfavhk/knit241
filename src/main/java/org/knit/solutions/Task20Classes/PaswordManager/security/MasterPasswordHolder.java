package org.knit.solutions.Task20Classes.PaswordManager.security;

import org.springframework.stereotype.Component;

@Component
public class MasterPasswordHolder {
    private char[] masterPassword;

    public MasterPasswordHolder(char[] masterPassword) {
        this.masterPassword = masterPassword;
    }

    public void changeMasterPassword(char[] masterPassword) {
        this.masterPassword = masterPassword;
    }
}
