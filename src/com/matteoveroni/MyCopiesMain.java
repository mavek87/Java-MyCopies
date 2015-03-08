package com.matteoveroni;

import com.matteoveroni.control.screen.ScreensController;
import com.matteoveroni.model.Model;
import com.matteoveroni.control.screen.ScreensControllerFactory;
import com.matteoveroni.model.ModelFactory;
import com.matteoveroni.model.commands.ExitCommand;
import com.matteoveroni.view.resources.ScreenResources;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matteo Veroni
 */
public class MyCopiesMain extends Application {

    private static final String APPLICATION_NAME = "MyCopies";
    private static final String APPLICATION_VERSION = "0.0.6";
    
    private final Model model;

    private static final Logger LOG = LoggerFactory.getLogger(MyCopiesMain.class);

    public MyCopiesMain(){
        model = ModelFactory.getInstance();
        LOG.debug("Model loaded");
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle(APPLICATION_NAME + " v. " + APPLICATION_VERSION);
        
        ScreensController mainController = ScreensControllerFactory.getInstance();
        mainController.setModel(model);
        mainController.setStage(stage);
        if (!mainController.loadScreen(ScreenResources.MAIN_SCREEN.screenName(), ScreenResources.MAIN_SCREEN.screenResource())) {
            throw new RuntimeException("Not able to load " + ScreenResources.MAIN_SCREEN.screenName() + ", the program will be closed!");
        }
        mainController.loadScreen(ScreenResources.COPY_SCREEN.screenName(), ScreenResources.COPY_SCREEN.screenResource());
        mainController.setScreen(ScreenResources.MAIN_SCREEN.screenName());

        Scene primaryScene = new Scene(mainController);
        LOG.debug("Primary scene created");

        stage.setScene(primaryScene);
        stage.show();
        LOG.debug("Primary scene setted and stage shown");
    }
    
    @Override
    public void stop(){
        new ExitCommand(model).execute();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
