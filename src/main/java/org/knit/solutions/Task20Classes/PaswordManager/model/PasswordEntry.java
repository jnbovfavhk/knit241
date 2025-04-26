package org.knit.solutions.Task20Classes.PaswordManager.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PasswordEntry {
    private final String site;
    private final String login;
    private final char[] encryptedPassword;
    private final Logger log = LoggerFactory.getLogger(PasswordEntry.class);

    public PasswordEntry(String site, String login, char[] encryptedPassword) {
        this.site = site;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
        log.debug("Объект PasswordEntry создан: " + this);
    }

    public PasswordEntry() {
        this.site = null;
        this.login = null;
        this.encryptedPassword = null;
    }

    @Override
    public String toString() {
        return "site='" + site + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    public String getSite() {
        return site;
    }

    public String getLogin() {
        return login;
    }

    public char[] getEncryptedPassword() {
        return encryptedPassword;
    }

    // Объекты сравниваются только по сайту
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordEntry that = (PasswordEntry) o;
        return Objects.equals(site, that.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(site);
    }
}
