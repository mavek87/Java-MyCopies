package com.matteoveroni;

import com.matteoveroni.control.screen.ScreensController;
import com.matteoveroni.model.Model;
import com.matteoveroni.control.screen.ScreensControllerFactory;
import com.matteoveroni.model.ModelFactory;
import com.matteoveroni.resources.ScreenResources;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matteo Veroni
 */
public class MyCopiesMain extends Application {

    private static final String APPLICATION_NAME = "MyCopies";
    private static final String APPLICATION_VERSION = "0.0.3";

    private static final Logger LOG = LoggerFactory.getLogger(MyCopiesMain.class);

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle(APPLICATION_NAME + " v. " + APPLICATION_VERSION);

        final Model model = ModelFactory.getInstance();
        LOG.debug("Model created");

        ScreensController mainController = ScreensControllerFactory.getInstance();
        mainController.setModel(model);
        mainController.setStage(stage);
        if(!mainController.loadScreen(ScreenResources.MAIN_SCREEN.screenName(), ScreenResources.MAIN_SCREEN.screenResource())){
            throw new RuntimeException("Not able to load " + ScreenResources.MAIN_SCREEN.screenName() + ", the program will be closed!");
        }
        mainController.setScreen(ScreenResources.MAIN_SCREEN.screenName());
        LOG.debug("Main Controller created and initializated");
        
        Group root = new Group();
        root.getChildren().addAll(mainController);
        
        Scene primaryScene = new Scene(root);
        LOG.debug("Primary scene created");
        
        stage.setScene(primaryScene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                model.dispose();
                LOG.debug("Model disposed");
                LOG.info("Exit");
            }
        });
        stage.show();
        LOG.debug("Primary scene setted and stage shown");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
