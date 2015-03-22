package com.matteoveroni.control;

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
