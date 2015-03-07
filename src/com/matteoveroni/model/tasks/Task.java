package com.matteoveroni.model.tasks;

import com.matteoveroni.model.commands.Action;
import com.matteoveroni.model.patterns.Observer;
import com.matteoveroni.model.patterns.Subject;
import java.util.ArrayList;
import java.util.Date;
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

    public enum LastStateAfterComplete {

        none, done, failed
    };

    private LastStateAfterComplete lastStateAfterComplete;

    private Date lastSuccesDate;
    private volatile Exception exceptionOccurred;

    public Task(String name, Action action) {
        this.ID = UUID.randomUUID();
        this.name = name;
        this.action = action;
        this.exceptionObservers = new ArrayList<>();
        this.exceptionOccurred = null;
        this.lastStateAfterComplete = LastStateAfterComplete.none;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                action.execute();
            } catch (Exception ex) {
                exceptionOccurred = ex;
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

    public LastStateAfterComplete getLastStateAfterComplete() {
        return lastStateAfterComplete;
    }

    public void setLastStateAfterComplete(LastStateAfterComplete lastStateAfterComplete) {
        this.lastStateAfterComplete = lastStateAfterComplete;
    }

    public Date getLastSuccesDate() {
        return lastSuccesDate;
    }

    public void setLastSuccesDate(Date lastSuccesDate) {
        this.lastSuccesDate = lastSuccesDate;
    }

    public Exception getExceptionOccurred() {
        return exceptionOccurred;
    }

}
