package com.matteoveroni.model;

import com.matteoveroni.interfaces.Disposable;
import com.matteoveroni.model.actions.Action;
import com.matteoveroni.model.actions.CopyAction;
import com.matteoveroni.model.copy.PathCopier;
import com.matteoveroni.model.tasks.Task;
import com.matteoveroni.model.tasks.TaskManager;
import com.matteoveroni.model.tasks.TaskManagerFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matteo Veroni
 */
public class Model implements Disposable {

    private final TaskManager taskManager = TaskManagerFactory.getInstance();
    private static final Logger LOG = LoggerFactory.getLogger(Model.class);

    public void simpleCopy(String taskName, File source, File target) throws Exception {

        Action copyAction = new CopyAction(new PathCopier(source, target));
        Task taskToDo = new Task(taskName, copyAction);
        taskManager.addTask(taskToDo);
        taskManager.scheduleTask(taskToDo, 0, TimeUnit.MILLISECONDS);
    }

    @Override
    public void dispose() {
        taskManager.dispose();
        LOG.debug("Model disposed");
    }
}
