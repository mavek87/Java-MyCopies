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
 *
 * @author Matteo Veroni
 */
public class MyCopies extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(MyCopies.class);
    
    @Override
    public void start(Stage stage) throws Exception {

        Model model = new Model();
        LOG.debug("Model created");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainMenuScreen.fxml"));
        Parent root = (Parent) loader.load();
        LOG.debug("View loaded");

        ScreenController mainMenuScreenController = loader.getController();
        mainMenuScreenController.setModel(model);
        LOG.debug("Model for Main Menu Screen Controller setted");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        LOG.debug("View created and shown");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
