package org.knit.solutions.Task20Classes.PaswordManager.service;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

@Service
public class ClipboardService {
    public static void copyToClipboard(char[] password) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(new StringSelection(new String(password)), null);
    }
}
