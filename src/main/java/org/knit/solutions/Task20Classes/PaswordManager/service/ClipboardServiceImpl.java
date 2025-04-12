package org.knit.solutions.Task20Classes.PaswordManager.service;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

@Service
public class ClipboardServiceImpl implements ClipboardService{
    private String lastCopied;


    @Override
    public void copyToClipboard(char[] password) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(new String(password)), null);
    }

    @Override
    public void clearClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            String current = (String) clipboard.getData(DataFlavor.stringFlavor);
            if (current != null && current.equals(lastCopied)) {
                clipboard.setContents(new StringSelection(""), null);
                lastCopied = null;
                System.out.println("Буфер обмена очищен.");
            } else {
                System.out.println("Буфер обмена был изменён вручную — не очищаем.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
