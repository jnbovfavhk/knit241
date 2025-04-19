package org.knit.solutions.Task20Classes.PaswordManager.service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

public interface EncryptionService {
    char[] encrypt(char[] input) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException;

    char[] decrypt(char[] input) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
}
