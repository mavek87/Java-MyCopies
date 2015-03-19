package com.matteoveroni.model.mainTest;

import com.matteoveroni.model.actions.Action;
import com.matteoveroni.model.actions.CopyAction;
import com.matteoveroni.model.actions.FakeErrorAfterSomeWorkAction;
import com.matteoveroni.model.actions.PrintMessageAction;
import com.matteoveroni.model.copy.PathCopier;
import com.matteoveroni.model.tasks.Task;
import com.matteoveroni.model.tasks.TaskManager;
import com.matteoveroni.model.tasks.TaskManagerFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author Matteo Veroni
 */
public class MyCopiesTest {

    public static void main(String[] args) throws InterruptedException {

        Action myPrintMessageAction = new PrintMessageAction("Ation1 message...");
        Action myPrintMessageAction2 = new PrintMessageAction("Action2 message...");

        Action copyAction = new CopyAction(new PathCopier(new File("/prova"), new File("/prova2")));

        Action errorAction = new FakeErrorAfterSomeWorkAction();

        Task taskToDo = new Task("print1", myPrintMessageAction);
        Task taskToDo2 = new Task("print2", myPrintMessageAction2);
        Task taskToDo3 = new Task("copia1", copyAction);
        Task taskToDo4 = new Task("error1", errorAction);

        TaskManager taskManager = TaskManagerFactory.getInstance();

        taskManager.scheduleTaskAtFixedRate(taskToDo, 2000, 1000, TimeUnit.MILLISECONDS);
        taskManager.scheduleTaskAtFixedRate(taskToDo2, 0, 500, TimeUnit.MILLISECONDS);
        taskManager.scheduleTask(taskToDo3, 2000, TimeUnit.MILLISECONDS);
        taskManager.scheduleTaskAtFixedRate(taskToDo4, 0, 200, TimeUnit.MILLISECONDS);

        Thread.sleep(8000);

        /*
         taskManager.searchTaskById(1);
         taskManager.cancelTask(1);

         Thread.sleep(5000);*/
        taskManager.dispose();

    }

}
