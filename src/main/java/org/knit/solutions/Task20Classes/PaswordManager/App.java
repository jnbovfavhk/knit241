package org.knit.solutions.Task20Classes.PaswordManager;

import javafx.application.Application;
import javafx.stage.Stage;
import org.knit.solutions.Task20Classes.PaswordManager.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App extends Application {

    private static AnnotationConfigApplicationContext context;

    @Override
    public void init() throws Exception {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Override
    public void start(Stage stage) throws Exception {


    }
}
