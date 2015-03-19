package com.matteoveroni;

import javafx.fxml.FXMLLoader;

/**
 * @author Matteo Veroni
 */
public class FXMLMainLoader {

    private static volatile FXMLMainLoader instance = null;

    private FXMLMainLoader() {}

    public static final synchronized FXMLMainLoader getInstance() {
        if (instance == null) {
            instance = new FXMLMainLoader();
        }
        return instance;
    }

    public FXMLLoader loadResource(String resource) {
        return new FXMLLoader(getClass().getResource(resource));
    }

}
