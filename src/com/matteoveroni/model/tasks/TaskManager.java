package com.matteoveroni.model.tasks;

import com.matteoveroni.model.designpatterns.Observer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Matteo Veroni
 */

public class TaskManager implements Observer {

    private final ScheduledExecutorService scheduler;
    private final Map<Task, ScheduledFuture> tasks;
    private static final int NUMBER_OF_PARALLEL_THREADS = 4;
    
    public TaskManager(){
        scheduler = Executors.newScheduledThreadPool(NUMBER_OF_PARALLEL_THREADS);
        tasks = new HashMap<>();
    }
    
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
    public void update(Object subject) {
        Task task;
        task = (Task) subject;
        
        ScheduledFuture scheduledFutureTaskToCancel = tasks.get(task);
        scheduledFutureTaskToCancel.cancel(true);
        
        System.out.println("caspius! durante l\'esecuzione del compito ID = " + task.getID()
                        + "\nName = " + task.getName() 
                        + "\nè avvenuta la seguente eccezione: " + task.getExceptionOccurred().toString());
        tasks.put(task, null);
    }

    public void dispose() {
        scheduler.shutdown();
        tasks.clear();
    }
    
    public void disposeImmediately() {
        scheduler.shutdownNow();
        tasks.clear();
    }

}
