package com.matteoveroni.control.screens;

/**
 * @author Matteo Veroni
 */
public class ScreensControllerFactory {

    private ScreensControllerFactory() {
    }

    public static ScreensController getInstance() {
        return new ScreensController();
    }

}
