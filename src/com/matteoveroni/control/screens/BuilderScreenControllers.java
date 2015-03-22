package com.matteoveroni.control.screens;

import com.matteoveroni.control.ScreensController;
import com.matteoveroni.control.ScreensControllerFactory;
import com.matteoveroni.model.Model;
import com.matteoveroni.view.resources.screen.ScreenResources;
import javafx.stage.Stage;

/**
 * @author Matteo Veroni
 */
public class BuilderScreenControllers {
    
    private final ScreensController mainController = ScreensControllerFactory.getInstance();
    
    public BuilderScreenControllers(Stage stage, Model model){
        mainController.setModel(model);
        mainController.setStage(stage);
    }
    
    public ScreensController build(){
        
        if (!mainController.loadScreen(ScreenResources.MAIN_SCREEN.screenName(), ScreenResources.MAIN_SCREEN.screenResource())) {
            throw new RuntimeException("Not able to load " + ScreenResources.MAIN_SCREEN.screenName() + ", the program will be closed!");
        }

        mainController.loadScreen(ScreenResources.COPY_SCREEN.screenName(), ScreenResources.COPY_SCREEN.screenResource());
        
        mainController.loadScreen(ScreenResources.TEST_SCREEN.screenName(), ScreenResources.TEST_SCREEN.screenResource());
        
        mainController.setScreen(ScreenResources.MAIN_SCREEN.screenName());
        
        return mainController;
    }
}
