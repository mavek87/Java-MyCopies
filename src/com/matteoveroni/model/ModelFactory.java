package com.matteoveroni.model;

/**
 * @author Matteo Veroni
 */
public class ModelFactory {
    
    private ModelFactory(){}
    
    public static final Model getInstance(){
        return new Model();
    }
}
