package org.knit.solutions.Task20Classes.PaswordManager;

import org.knit.solutions.Task20Classes.PaswordManager.model.PasswordEntry;
import org.knit.solutions.Task20Classes.PaswordManager.service.AESEncryptionService;
import org.knit.solutions.Task20Classes.PaswordManager.service.PasswordService;
import org.knit.solutions.Task20Classes.PaswordManager.ui.ConsoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class PmApplicationListener {

    @Autowired
    PasswordService passwordService;

    @Autowired
    AESEncryptionService encryptionService;

    @Autowired
    ConsoleInterface consoleInterface;


    @EventListener
    public void init(ContextRefreshedEvent event) {
        System.out.println("Приложение готово к работе");

        consoleInterface.start();

//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Введите мастер-пароль: ");
//        char[] masterPassword = scanner.nextLine().toCharArray();
//        encryptionService.setSecretKey(masterPassword);
//
//        System.out.println();
//
//        while (true) {
//            System.out.println("1 Добавить или обновить запись");
//            System.out.println("2 Посмотреть все записи");
//            System.out.println("3 Удалить запись(по названию сайта)");
//            System.out.println("4 Выйти");
//
//
//            int option = scanner.nextInt();
//            scanner.nextLine();
//
//            if (option == 1) {
//                System.out.println("Введите сайт:");
//                String site = scanner.nextLine();
//                System.out.println("Введите логин:");
//                String login = scanner.nextLine();
//                System.out.println("Введите пароль:");
//                char[] password = scanner.nextLine().toCharArray();
//
//                try {
//                    passwordService.add(site, login, password);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//
//            if (option == 2) {
//                ArrayList<PasswordEntry> notes = passwordService.getList();
//                for (PasswordEntry entry : notes) {
//                    System.out.println(entry);
//                }
//            }
//
//            if (option == 3) {
//                System.out.println("Введите сайт:");
//                String site = scanner.nextLine();
//                try {
//                    passwordService.delete(site);
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            if (option == 4) {
//                return;
//            }
//
//        }
    }

}
