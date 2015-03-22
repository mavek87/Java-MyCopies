package com.matteoveroni.control.screens;

import com.matteoveroni.control.ScreensController;
import com.matteoveroni.model.Model;
import com.matteoveroni.view.resources.screen.ScreenResources;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Matteo Veroni
 */
public class MainScreenController implements ScreenControllable, ScreenSettable  {
        
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button goToCopyScreenButton;
    @FXML
    private TableView<?> ScheduledTasksTable;
    
    private Model model;
    private Stage stage;
    private ScreensController myController;

    private static final Logger LOG = LoggerFactory.getLogger(MainScreenController.class);

    @Override
    public void setScreensController(ScreensController screensController) {
        myController = screensController;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    void goToCopyScreen(ActionEvent event) {
        myController.setScreen(ScreenResources.COPY_SCREEN.screenName());
    }	
    
    @FXML
    void initialize() {
        assert goToCopyScreenButton != null : "fx:id=\"goToCopyScreenButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert ScheduledTasksTable != null : "fx:id=\"ScheduledTasksTable\" was not injected: check your FXML file 'MainScreen.fxml'.";
    }

}