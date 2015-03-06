package com.matteoveroni.model;

import com.matteoveroni.model.commoninterfaces.Disposable;
import com.matteoveroni.model.actions.Action;
import com.matteoveroni.model.actions.CopyAction;
import com.matteoveroni.model.copy.PathCopier;
import com.matteoveroni.model.tasks.Task;
import com.matteoveroni.model.tasks.TaskManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Matteo Veroni
 */

public class Model implements Disposable{

    private final TaskManager taskManager = new TaskManager();
    
    public void simpleCopy(String taskName, File source, File target) throws Exception {

        Action copyAction = new CopyAction(new PathCopier(source, target));
        Task taskToDo = new Task(taskName, copyAction);
        taskManager.scheduleTask(taskToDo, 0, TimeUnit.MILLISECONDS);
        
        
        /*PathCopier pathCopier = new PathCopier(source, target);
        pathCopier.copy();*/

    }
    
    @Override
    public void dispose(){
        taskManager.dispose();
    }
}
