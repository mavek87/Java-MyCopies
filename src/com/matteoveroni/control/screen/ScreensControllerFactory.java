package com.matteoveroni.control.screen;

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
