package com.matteoveroni.control.screens;

import com.matteoveroni.control.ScreensController;
import com.matteoveroni.model.Model;
import com.matteoveroni.view.resources.screen.ScreenResources;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class MainScreenController implements ScreenControllable, ScreenSettable, Initializable  {
        
    private Model model;
    private Stage stage;
    private ScreensController myController;
    
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button NewTaskButton;
    @FXML
    private Button CopyAFileButton;
    @FXML
    private TableView<?> ScheduledTasksTable;    

    private static final Logger LOG = LoggerFactory.getLogger(MainScreenController.class);
    
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
    
    @FXML
    void goToTaskScreen(ActionEvent event) {
         myController.setScreen(ScreenResources.TASK_SCREEN.screenName());
    }
    
}