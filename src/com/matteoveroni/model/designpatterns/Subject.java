package com.matteoveroni.model.designpatterns;

/**
 * @author Matteo Veroni
 */

public interface Subject {

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();
}
