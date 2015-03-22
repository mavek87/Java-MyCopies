package com.matteoveroni.model.tasks;

import com.matteoveroni.model.actions.Action;
import com.matteoveroni.model.patterns.Observer;
import com.matteoveroni.model.patterns.Subject;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

/**
 * @author Matteo Veroni
 */
public class Task implements Runnable, Subject {

    private final UUID taskID;
    private String taskName;
    private final Action taskAction;

    private final List<Observer> exceptionObservers;

    public enum LastOperationStatus {

        none, done, failed
    };

    private volatile LastOperationStatus lastOperationStatus = LastOperationStatus.none;
    private volatile Date lastSuccessfullOperationDate;
    private volatile Exception exceptionOccurred;

    public Task(String taskName, Action taskAction) {
        this.taskID = UUID.randomUUID();
        this.taskName = taskName;
        this.taskAction = taskAction;
        this.exceptionObservers = new ArrayList<>();
        this.exceptionOccurred = null;
        this.lastOperationStatus = LastOperationStatus.none;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                taskAction.execute();
                lastOperationStatus = LastOperationStatus.done;
                lastSuccessfullOperationDate = GregorianCalendar.getInstance().getTime();
            } catch (Exception ex) {
                exceptionOccurred = ex;
                lastOperationStatus = LastOperationStatus.failed;
                notifyObservers();
                removeAllTheObservers();
            }
        }
    }

    @Override
    public void registerObserver(Observer exceptionObserver) {
        if (!exceptionObservers.contains(exceptionObserver)) {
            exceptionObservers.add(exceptionObserver);
        }
    }

    @Override
    public void removeObserver(Observer exceptionObserver) {
        exceptionObservers.remove(exceptionObserver);
    }

    @Override
    public void notifyObservers() {
        for (Observer exceptionObserver : exceptionObservers) {
            exceptionObserver.update(this);
        }
    }

    private void removeAllTheObservers() {
        exceptionObservers.removeAll(exceptionObservers);
    }

    // Public Task getters and setters:
    public UUID getID() {
        return taskID;
    }

    public String getName() {
        return taskName;
    }

    public void setName(String name) {
        this.taskName = name;
    }

    public LastOperationStatus getLastStateAfterComplete() {
        return lastOperationStatus;
    }

    public Date getLastSuccesDate() {
        return lastSuccessfullOperationDate;
    }

    public Exception getException() {
        return exceptionOccurred;
    }

}
