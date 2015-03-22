package com.matteoveroni.control.screens;

import com.matteoveroni.control.ScreensController;
import com.matteoveroni.model.Model;
import com.matteoveroni.view.resources.screen.ScreenResources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Matteo Veroni
 */
public class TaskScreenController implements ScreenSettable, ScreenControllable{

    private Model model;
    private Stage stage;
    private ScreensController myController;
    
    @FXML
    private Button OkButton;
    @FXML
    private TextArea NotesTextArea;
    @FXML
    private Button CancelButton;
    @FXML
    private ChoiceBox<?> TypeOfTaskComboBox;
    @FXML
    private TextField taskNameTextField;
    
    private static final Logger LOG = LoggerFactory.getLogger(TaskScreenController.class);
    
    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setScreensController(ScreensController screensController) {
        myController = screensController;
    }
    
    @FXML
    void cancelGoToMainScreen(ActionEvent event) {
        myController.setScreen(ScreenResources.MAIN_SCREEN.screenName());
    }	
    
}