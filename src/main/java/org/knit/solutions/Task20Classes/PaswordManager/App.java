package org.knit.solutions.Task20Classes.PaswordManager;

import javafx.application.Application;
import javafx.stage.Stage;
import org.knit.solutions.Task20Classes.PaswordManager.config.AppConfig;
import org.knit.solutions.Task20Classes.PaswordManager.security.MasterPasswordHolder;
import org.knit.solutions.Task20Classes.PaswordManager.service.AESEncryptionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.Scanner;

public class App  {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }

    public void start(Stage stage) throws Exception {
        //getMasterPassword();

    }

    private void getMasterPassword() throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
//        MasterPasswordHolder passwordHolder = context.getBean(MasterPasswordHolder.class);


  //      passwordHolder.setMasterPassword(masterPassword);
    }
}
