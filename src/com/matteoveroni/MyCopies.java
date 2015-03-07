package com.matteoveroni;

import com.matteoveroni.control.screen.ScreensController;
import com.matteoveroni.model.Model;
import com.matteoveroni.control.screen.ScreensControllerFactory;
import com.matteoveroni.model.ModelFactory;
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
public class MyCopies extends Application {

    private static final String APPLICATION_NAME = "MyCopies";
    private static final String APPLICATION_VERSION = "0.0.2";

    private static final Logger LOG = LoggerFactory.getLogger(MyCopies.class);

    private static final String MAIN_MENU_SCREEN_RESOURCE = "view/MainMenuScreen.fxml";

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle(APPLICATION_NAME + "v." + APPLICATION_VERSION);

        final Model model = ModelFactory.getInstance();
        LOG.debug("Model created");

        ScreensController mainController = ScreensControllerFactory.getInstance();
        mainController.setModel(model);
        mainController.setStage(stage);
        if(!mainController.loadScreen("Main Screen", "view/MainMenuScreen.fxml")){
            throw new RuntimeException("Not able to load Main Menu Screen");
        }
        mainController.setScreen("Main Screen");
        LOG.debug("Main Controller created and initializated");

        /*FXMLLoader viewLoader = new FXMLLoader(getClass().getResource(MAIN_MENU_SCREEN_RESOURCE));
         LOG.debug("View loaded");

         Parent root = (Parent) viewLoader.load();
         LOG.debug("XML Root loaded");

         ScreenController mainMenuScreenController = viewLoader.getController();
         LOG.debug("Main Menu ScreenController created");

         mainMenuScreenController.setModel(model);
         mainMenuScreenController.setStage(stage);*/
        
        Group root = new Group();
        root.getChildren().addAll(mainController);
        
        Scene primaryScene = new Scene(root);
        LOG.debug("Main Menu scene created");
        
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
        LOG.debug("Main Menu scene setted and stage shown");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
