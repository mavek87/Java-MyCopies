package com.matteoveroni;

import javafx.fxml.FXMLLoader;

/**
 * @author Matteo Veroni
 */
public class FXMLMainLoader{
    
    public FXMLLoader loadResource(String resource){
        return new FXMLLoader(getClass().getResource(resource));
    }
    
}
