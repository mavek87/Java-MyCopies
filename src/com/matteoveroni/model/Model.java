package com.matteoveroni.model;

import com.matteoveroni.model.copy.PathCopier;
import com.matteoveroni.model.tasks.Task;
import com.matteoveroni.model.tasks.TaskManager;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

/**
 * @author Matteo Veroni
 */

public class Model {

    private final TaskManager taskManager = new TaskManager();
    private Stack<Task> tasksToDo;

    /*public void setAction(Action actionToDo) {
        tasksToDo.push(new OldTask(actionToDo));
    }

    public void performAction() {
        taskManager.scheduleTask(tasksToDo.pop(), 0);
    }*/

    public void simpleCopy(File source, File target) throws IOException {

        PathCopier pathCopier = new PathCopier(source, target);
        pathCopier.copy();
        
        /*Action copyAction = new CopyAction(new PathCopier(source, target));
        taskManager.scheduleTask(new Task(copyAction), 0);*/

    }

}
