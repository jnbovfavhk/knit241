package org.knit.solutions.Task20Classes.PaswordManager;

import org.knit.solutions.Task20Classes.PaswordManager.security.MasterPasswordHolder;
import org.knit.solutions.Task20Classes.PaswordManager.service.AESEncryptionService;
import org.knit.solutions.Task20Classes.PaswordManager.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Scanner;

@Component
public class PmApplicationListener {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    AESEncryptionService encryptionService;


    @EventListener
    public void init(ContextRefreshedEvent event){
        System.out.println("Приложение готово к работе");



        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите мастер-пароль: ");
        char[] masterPassword = scanner.nextLine().toCharArray();
        System.out.println();

        System.out.println("1 Добавить запись");
        System.out.println("2 Посмотреть все записи");

        int option = scanner.nextInt();

        if (option == 1){
            System.out.println("Введите логин:");
            String login = scanner.nextLine();
            System.out.println("Введите пароль:");
            char[] password = scanner.nextLine().toCharArray();


            try {
                encryptionService.changeSecretKey(masterPassword);
                passwordService.add(login, password );

            } catch (IOException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
                throw new RuntimeException(e);
            }


        }
    }

}
