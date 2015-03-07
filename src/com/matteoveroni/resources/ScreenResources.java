package com.matteoveroni.resources;

/**
 * @author Matteo Veroni
 */
public enum ScreenResources {
    
    MAIN_SCREEN ("Main Screen", "view/MainMenuScreen.fxml");

    private final String screenName;
    private final String screenResource;
    
    ScreenResources(String screenName, String screenResource) {
        this.screenName = screenName;
        this.screenResource = screenResource;
    }
    
    public String screenName() { return screenName; }
    public String screenResource() { return screenResource; }
    
}
