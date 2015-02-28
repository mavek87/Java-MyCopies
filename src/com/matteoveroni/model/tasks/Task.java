package com.matteoveroni.model.tasks;

import com.matteoveroni.model.actions.Action;
import java.util.TimerTask;

/**
 *
 * @author Matteo Veroni
 */
public class Task extends TimerTask {

    private final Action action;
    private static int privateIdCounter = 0;
    private final int id;

    public Task(Action action) {
        this.action = action;
        privateIdCounter++;
        this.id = privateIdCounter;
    }

    @Override
    public void run(){
        action.execute();
    }

    public int getId() {
        return id;
    }
}
