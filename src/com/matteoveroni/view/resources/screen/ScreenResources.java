package com.matteoveroni.view.resources.screen;

/**
 * @author Matteo Veroni
 */
public enum ScreenResources {
    
    MAIN_SCREEN ("Main Screen", "view/MainScreen.fxml"),
    COPY_SCREEN ("Copy Screen", "view/CopyScreen.fxml"),
    TASK_SCREEN ("Action Screen", "view/TaskScreen.fxml");

    private final String screenName;
    private final String screenResource;
    
    ScreenResources(String screenName, String screenResource) {
        this.screenName = screenName;
        this.screenResource = screenResource;
    }
    
    public String screenName() { return screenName; }
    public String screenResource() { return screenResource; }
    
}
