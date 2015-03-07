package com.matteoveroni.model.patterns;

/**
 * @author Matteo Veroni
 */
public interface Subject {

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();
}
