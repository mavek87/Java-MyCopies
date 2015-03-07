package com.matteoveroni;

/**
 * @author Matteo Veroni
 */
public class FXMLMainLoaderFactory {

    private FXMLMainLoaderFactory() {
    }

    public static FXMLMainLoader getInstance() {
        return new FXMLMainLoader();
    }
}
