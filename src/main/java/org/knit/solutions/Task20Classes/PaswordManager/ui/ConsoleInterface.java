package org.knit.solutions.Task20Classes.PaswordManager.ui;

import org.knit.solutions.Task20Classes.PaswordManager.model.PasswordEntry;
import org.knit.solutions.Task20Classes.PaswordManager.security.MasterPasswordManager;
import org.knit.solutions.Task20Classes.PaswordManager.service.AESEncryptionService;
import org.knit.solutions.Task20Classes.PaswordManager.service.ClipboardServiceImpl;
import org.knit.solutions.Task20Classes.PaswordManager.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class ConsoleInterface {

    @Autowired
    Scanner scanner;

    @Autowired
    PasswordService passwordService;

    @Autowired
    AESEncryptionService encryptionService;

    @Autowired
    MasterPasswordManager masterPasswordManager;

    @Autowired
    ClipboardServiceImpl clipboardService;

    @Autowired
    Console console;


    public void start() {


        System.out.print("Введите мастер-пароль: ");
        boolean passIsRight = checkMasterPassword();
        while (!passIsRight) {
            System.out.println("Неправильно. Введите мастер-пароль:");
            passIsRight = checkMasterPassword();
        }
        ;


        System.out.println();

        while (true) {
            System.out.println("1 Добавить или обновить запись");
            System.out.println("2 Посмотреть все записи");
            System.out.println("3 Удалить запись(по названию сайта)");
            System.out.println("4 Копировать пароль в буфер обмена(по названию сайта)");
            System.out.println("5 Выйти");


            int option = scanner.nextInt();
            scanner.nextLine();


            if (option == 1) {
                addOrDelete();
            }

            if (option == 2) {
                showAll();
            }

            if (option == 3) {
                deleteNote();
            }

            if (option == 4) {
                copyToClipboard();
            }

            if (option == 5) {
                return;
            }

        }
    }

    private void copyToClipboard() {
        System.out.println("Введите название сайта:");
        String site = scanner.nextLine();
        char[] pass = passwordService.getPassword(site);
        clipboardService.copyToClipboard(pass);

        System.out.println("Пароль скопирован в буфер обмена. Пользуйтесь!");
    }


    public void addOrDelete() {
        System.out.println("Введите сайт:");
        String site = scanner.nextLine();
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        char[] password = console.readPassword();

        try {
            passwordService.add(site, login, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAll() {
        ArrayList<PasswordEntry> notes = passwordService.getList();
        for (PasswordEntry entry : notes) {
            System.out.println(entry);
        }
    }

    public void deleteNote() {
        System.out.println("Введите сайт:");
        String site = scanner.nextLine();
        passwordService.delete(site);

    }

    public boolean checkMasterPassword() {

        char[] masterPassword = console.readPassword();
        return masterPasswordManager.isMasterPasswordSameToFile(masterPassword);
    }
}
