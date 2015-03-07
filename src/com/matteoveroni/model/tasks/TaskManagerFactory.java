package com.matteoveroni.model.tasks;

/**
 * @author Matteo Veroni
 */
public class TaskManagerFactory {

    private TaskManagerFactory() {
    }

    public static TaskManager getInstance() {
        return new TaskManager();
    }
}
