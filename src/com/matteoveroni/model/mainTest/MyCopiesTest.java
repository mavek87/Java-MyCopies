package com.matteoveroni.model.mainTest;

import com.matteoveroni.model.actions.Action;
import com.matteoveroni.model.actions.CopyAction;
import com.matteoveroni.model.actions.PrintMessageAction;
import com.matteoveroni.model.copy.PathCopier;
import com.matteoveroni.model.tasks.Task;
import com.matteoveroni.model.tasks.TaskManager;
import java.io.File;

/**
 *
 * @author Matteo Veroni
 */
public class MyCopiesTest {

    public static void main(String[] args) throws InterruptedException {

        Action myPrintMessageAction = new PrintMessageAction("Messaggio standard");
        Action myPrintMessageAction2 = new PrintMessageAction("Messaggio 2");

        Action copia = new CopyAction(new PathCopier(new File("/prova"), new File("/prova2")));

        Task compitoDaFare = new Task(myPrintMessageAction);
        Task compitoDaFare2 = new Task(myPrintMessageAction2);
        Task compitoDaFare3 = new Task(copia);

        TaskManager taskManager = new TaskManager();

        taskManager.scheduleTask(compitoDaFare, 2000, 1000);
        taskManager.scheduleTask(compitoDaFare2, 0, 500);
        taskManager.scheduleTask(compitoDaFare3, 2000);

        Thread.sleep(6000);

        taskManager.searchTaskById(1);
        taskManager.cancelTask(1);

        Thread.sleep(5000);
        taskManager.destroy();

    }

}
