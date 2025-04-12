package org.knit.solutions.Task20Classes.PaswordManager.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@Configuration
@ComponentScan(basePackages = "org.knit.solutions.Task20Classes.PaswordManager")
public class AppConfig {


    @Bean
    public Cipher cipherService() {
        try {
            return Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
