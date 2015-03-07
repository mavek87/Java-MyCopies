package com.matteoveroni.model;

/**
 * @author Matteo Veroni
 */
public class ModelFactory {
    
    private ModelFactory(){}
    
    public static Model getInstance(){
        return new Model();
    }
}
