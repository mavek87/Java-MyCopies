package com.matteoveroni;

import com.matteoveroni.model.Model;
import com.matteoveroni.control.screen.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matteo Veroni
 */

public class MyCopies extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(MyCopies.class);
    private static final String APPLICATION_NAME = "MyCopies";
    private static final String APPLICATION_VERSION = "0.0.1";

    @Override
    public void start(Stage stage) throws Exception {
        final Model model = new Model();
        LOG.debug("Model created");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainMenuScreen.fxml"));
        Parent root = (Parent) loader.load();
        LOG.debug("View loaded");

        ScreenController mainMenuScreenController = loader.getController();
        mainMenuScreenController.setModel(model);
        LOG.debug("Model for Main Menu Screen Controller setted");

        Scene mainScene = new Scene(root);
        
        stage.setTitle(APPLICATION_NAME + "v." + APPLICATION_VERSION);
        stage.setScene(mainScene);
        stage.show();
        LOG.debug("View created and shown");

        mainMenuScreenController.setStage(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
