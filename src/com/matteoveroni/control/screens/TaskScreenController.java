package com.matteoveroni.control.screens;

import com.matteoveroni.control.ScreensController;
import com.matteoveroni.model.Model;
import com.matteoveroni.model.tasks.TaskType;
import com.matteoveroni.view.resources.screen.ScreenResources;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
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
public class TaskScreenController implements ScreenSettable, ScreenControllable, Initializable {

    private Model model;
    private Stage stage;
    private ScreensController myController;
    private String TaskName;
    private String Notes;
    private TaskType taskSelected;

    @FXML
    private Button OkButton;
    @FXML
    private TextArea NotesTextArea;
    @FXML
    private Button CancelButton;
    @FXML
    private ComboBox<TaskType> TypeOfTaskComboBox;
    @FXML
    private TextField taskNameTextField;

    private static final Logger LOG = LoggerFactory.getLogger(TaskScreenController.class);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeOfTaskComboBox.setPromptText("Select a Task Type");
        TypeOfTaskComboBox.setItems(FXCollections.observableArrayList(TaskType.values()));
    }

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

    @FXML
    void okGoToSpecificTaskScreen(ActionEvent event) {
        if (taskSelected != null) {

            taskSelected = TypeOfTaskComboBox.valueProperty().get();

            switch (taskSelected) {
                case singlecopy:
                    myController.setScreen(ScreenResources.COPY_SCREEN.screenName());
                    break;
                case scheduledcopy:
                    Dialogs.showWarningDialog(stage,
                        "This function is not yet implemented. You will be redirected back to the main menu.",
                        "Warning",
                        "Function not yet implemented");
                    myController.setScreen(ScreenResources.MAIN_SCREEN.screenName());
                    break;
                case alarm:
                    Dialogs.showWarningDialog(stage,
                        "This function is not yet implemented. You will be redirected back to the main menu.",
                        "Warning",
                        "Function not yet implemented");
                    myController.setScreen(ScreenResources.MAIN_SCREEN.screenName());
                    break;
                default:
                    LOG.warn("You have to select a type of Task");
                    break;
            }

        } else {
            Dialogs.showWarningDialog(stage, "You haven\'t selected any Task Type", "Warning", "Task Type not selected!");
        }

    }

    @FXML
    void selectedNewTypeOfTask(ActionEvent event) {
        taskSelected = TypeOfTaskComboBox.valueProperty().get();
        switch (taskSelected) {
            case singlecopy:
                LOG.info("Selected \'" + taskSelected + "\' task");
                break;
            case scheduledcopy:
                LOG.info("Selected \'" + taskSelected + "\' task");
                break;
            case alarm:
                LOG.info("Selected \'" + taskSelected + "\' task");
                break;
            default:
                LOG.warn("You have to select a type of Task");
                break;
        }
    }
}
