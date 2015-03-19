package com.matteoveroni.control.screens;

import com.matteoveroni.model.Model;
import com.matteoveroni.view.resources.ScreenResources;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Matteo Veroni
 */
public class MainScreenController implements Initializable, ControllableScreen, ScreenController  {
        
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button goToCopyScreenButton;
    
    private Model model;
    private Stage stage;
    private ScreensController myController;

    private static final Logger LOG = LoggerFactory.getLogger(CopyScreenController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

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

}
