package com.matteoveroni.model.tasks;

import com.matteoveroni.interfaces.Disposable;
import com.matteoveroni.model.patterns.Observer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matteo Veroni
 */
public class TaskManager implements Observer, Disposable {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(NUMBER_OF_PARALLEL_THREADS);
    private static final int NUMBER_OF_PARALLEL_THREADS = 4;
    private final Map<Task, ScheduledFuture> tasks = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(TaskManager.class);

    public void addTask(Task task) {
        if (this.containsTask(task)) {
            LOG.debug("Task \'" + task.getName() + "\' is already present, cannot add another clone");
        } else {
            tasks.put(task, null);
            LOG.debug("Task \'" + task.getName() + "\' added");
        }
    }

    public void removeTask(Task task) {
        if (this.containsTask(task)) {
            tasks.remove(task);
            LOG.debug("Task \'" + task.getName() + "\' removed");
        } else {
            LOG.debug("Task \'" + task.getName() + "\' is not present, cannot be removed");
        }

    }
    
    public boolean containsTask(Task task){
        return tasks.containsKey(task);
    }

    public void scheduleTask(Task task, long delay, TimeUnit timeUnit) {
        if (this.containsTask(task)) {
            observeExceptions(task);
            ScheduledFuture scheduledFutureTask = scheduler.schedule(task, delay, timeUnit);
            tasks.put(task, scheduledFutureTask);
            LOG.debug("Task \'" + task.getName() + "\' scheduled");
        } else {
            LOG.debug("Task \'" + task.getName() + "\' was not added to TaskManager so it cannot be scheduled");
        }
    }

    public void scheduleTaskAtFixedRate(Task task, long initialDelay, long delay, TimeUnit timeUnit) {
        if (this.containsTask(task)) {
            observeExceptions(task);
            ScheduledFuture scheduledFutureTask = scheduler.scheduleAtFixedRate(task, initialDelay, delay, timeUnit);
            tasks.put(task, scheduledFutureTask);
            LOG.debug("Task \'" + task.getName() + "\' scheduled at fixed rate");
        } else {
            LOG.debug("Task \'" + task.getName() + "\' was not added to TaskManager so it cannot be scheduled at fixed rate");
        }
    }

    private void observeExceptions(Task task) {
        task.registerObserver(this);
    }

    @Override
    public void update(Object objectTaskWithException) {
        
        Task taskWithException = (Task) objectTaskWithException;

        tasks.get(taskWithException).cancel(true);

        LOG.error("Task ID: " + taskWithException.getID()
                   + " Name: " + taskWithException.getName()
                   + " " + taskWithException.getException().toString());

        tasks.put(taskWithException, null);
    }

    @Override
    public void dispose() {
        scheduler.shutdown();
        tasks.clear();
    }

    public void disposeImmediately() {
        scheduler.shutdownNow();
        tasks.clear();
    }

}
