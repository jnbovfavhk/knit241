package org.knit.solutions.Task20Classes.PaswordManager.security;

import org.knit.solutions.Task20Classes.PaswordManager.service.EncryptionService;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;


public class MasterPasswordHolder {
    private char[] masterPassword;
    private EncryptionService encryptionService;

    public MasterPasswordHolder(char[] masterPassword) {
        this.masterPassword = masterPassword;

    }

    public void setMasterPassword(char[] masterPassword) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        this.masterPassword = encryptionService.encrypt(masterPassword);
    }


    public char[] getMasterPassword() throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return encryptionService.decrypt(masterPassword);
    }
}
