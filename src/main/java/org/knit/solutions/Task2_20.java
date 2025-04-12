package org.knit.solutions;

import org.knit.TaskDescription;
import org.knit.solutions.Task20Classes.PaswordManager.App;

@TaskDescription(taskNumber = 20, taskDescription = "Приложение на Spring",
        href = "org/knit/solutions/Task20Classes/PaswordManager")
public class Task2_20 implements Solution {
    @Override
    public void execute() throws Exception {
        App.main(null);
        //app.start(new Stage());
    }
}
