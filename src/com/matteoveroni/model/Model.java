package com.matteoveroni.model;

import com.matteoveroni.commons.interfaces.Disposable;
import com.matteoveroni.model.commands.Action;
import com.matteoveroni.model.commands.CopyAction;
import com.matteoveroni.model.copy.PathCopier;
import com.matteoveroni.model.tasks.Task;
import com.matteoveroni.model.tasks.TaskManager;
import com.matteoveroni.model.tasks.TaskManagerFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author Matteo Veroni
 */
public class Model implements Disposable {

    private final TaskManager taskManager = TaskManagerFactory.getInstance();

    public void simpleCopy(String taskName, File source, File target) throws Exception {

        Action copyAction = new CopyAction(new PathCopier(source, target));
        Task taskToDo = new Task(taskName, copyAction);
        taskManager.scheduleTask(taskToDo, 0, TimeUnit.MILLISECONDS);

        /*PathCopier pathCopier = new PathCopier(source, target);
         pathCopier.copy();*/
    }

    @Override
    public void dispose() {
        taskManager.dispose();
    }
}
