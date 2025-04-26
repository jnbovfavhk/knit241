package org.knit.solutions.Task20Classes.PaswordManager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ClipboardServiceImpl implements ClipboardService {
    private final Logger log = LoggerFactory.getLogger(ClipboardServiceImpl.class);

    @Override
    public void copyToClipboard(char[] password) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(new String(password)), null);
        log.info("Некий пароль скопирован в буфер обмена");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.schedule(this::clearClipboard, 10, TimeUnit.SECONDS);
        scheduler.shutdown();

    }

    @Override
    public void clearClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            clipboard.setContents(new StringSelection(""), null);
            log.info("Буфер обмена очищен");
            System.out.println("Буфер обмена очищен.");

        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
    }
}
