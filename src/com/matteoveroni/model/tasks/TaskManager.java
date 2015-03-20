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

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(NUMBER_OF_PARALLEL_THREADS);;
    private final Map<Task, ScheduledFuture> tasks = new HashMap<>();
    private static final int NUMBER_OF_PARALLEL_THREADS = 4;
    private static final Logger LOG = LoggerFactory.getLogger(TaskManager.class);

    public void scheduleTask(Task task, long delay, TimeUnit timeUnit) {
        observeExceptions(task);
        ScheduledFuture scheduledFutureTask = scheduler.schedule(task, delay, timeUnit);
        tasks.put(task, scheduledFutureTask);
    }

    public void scheduleTaskAtFixedRate(Task task, long initialDelay, long delay, TimeUnit timeUnit) {
        observeExceptions(task);
        ScheduledFuture scheduledFutureTask = scheduler.scheduleAtFixedRate(task, initialDelay, delay, timeUnit);
        tasks.put(task, scheduledFutureTask);
    }

    private void observeExceptions(Task task) {
        task.registerObserver(this);
    }

    @Override
    public void update(Object task){
        Task taskWithException = (Task) task;

        ScheduledFuture scheduledFutureTaskToCancel = tasks.get(taskWithException);
        scheduledFutureTaskToCancel.cancel(true);

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
