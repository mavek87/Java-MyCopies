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

    private final Action action;
    private final UUID ID;
    private String name;
    private final List<Observer> exceptionObservers;

    public enum LastOperationStatus {

        none, done, failed
    };

    private volatile LastOperationStatus lastOperationStatus = LastOperationStatus.none;
    private volatile Date lastSuccessfullOperationDate;
    private volatile Exception exceptionOccurred;

    public Task(String name, Action action) {
        this.ID = UUID.randomUUID();
        this.name = name;
        this.action = action;
        this.exceptionObservers = new ArrayList<>();
        this.exceptionOccurred = null;
        this.lastOperationStatus = LastOperationStatus.none;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                action.execute();
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
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
