package org.knit.solutions.Task20Classes.PaswordManager;

import org.knit.solutions.Task20Classes.PaswordManager.ui.ConsoleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PmApplicationListener {

    @Autowired
    ConsoleInterface consoleInterface;

    Logger log = LoggerFactory.getLogger(PmApplicationListener.class);


    @EventListener
    public void init(ContextRefreshedEvent event) {
        System.out.println("Приложение готово к работе");
        log.info("Приложение запущено и готов к работе");
        consoleInterface.start();

    }

}
