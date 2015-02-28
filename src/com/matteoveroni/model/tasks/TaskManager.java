package com.matteoveroni.model.tasks;

import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;

/**
 *
 * @author Matteo Veroni
 */
public class TaskManager {

    private final Timer timer = new Timer();
    private final LinkedList<Task> taskList = new LinkedList<>();

    public void scheduleTask(Task task, Date time) {
        taskList.add(task);
        timer.schedule(task, time);
    }

    public void scheduleTask(Task task, long delay) {
        taskList.add(task);
        timer.schedule(task, delay);
    }

    public void scheduleTask(Task task, Date firstTime, long period) {
        taskList.add(task);
        timer.schedule(task, firstTime, period);
    }

    public void scheduleTask(Task task, long delay, long period) {
        taskList.add(task);
        timer.schedule(task, delay, period);
    }

    public void scheduleTaskAtFixedRate(Task task, Date firstTime, long period) {
        taskList.add(task);
        timer.scheduleAtFixedRate(task, firstTime, period);
    }

    public void scheduleTaskAtFixedRate(Task task, long delay, long period) {
        taskList.add(task);
        timer.scheduleAtFixedRate(task, delay, period);
    }

    public void cancelTask(int idOfTaskToStop) {
        Task taskToStop = searchTaskById(idOfTaskToStop);
        if (taskToStop != null) {
            taskToStop.cancel();
            taskList.remove(taskToStop.getId());
        }
    }

    public Task searchTaskById(int idOfSearchedTask) {
        for (Task task : taskList) {
            if (task.getId() == idOfSearchedTask) {
                return task;
            }
        }
        return null;
    }

    public void destroy() {
        timer.purge();
        timer.cancel();
    }

}
