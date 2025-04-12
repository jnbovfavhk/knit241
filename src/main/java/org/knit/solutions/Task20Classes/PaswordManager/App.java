package org.knit.solutions.Task20Classes.PaswordManager;

import org.knit.solutions.Task20Classes.PaswordManager.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;

public class App {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }

    private void getMasterPassword() throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
//        MasterPasswordHolder passwordHolder = context.getBean(MasterPasswordHolder.class);


        //      passwordHolder.setMasterPassword(masterPassword);
    }
}
