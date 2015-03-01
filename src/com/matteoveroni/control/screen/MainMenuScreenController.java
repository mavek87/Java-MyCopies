package com.matteoveroni.control.screen;

import com.matteoveroni.model.Model;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Matteo Veroni
 */
public class MainMenuScreenController implements Initializable, ScreenController {

    @FXML
    private Button chooseSourcePathButton;
    @FXML
    private Button chooseTargetPathButton;
    @FXML
    private Button startCopyButton;
    @FXML
    private TextField sourcePathTextField;
    @FXML
    private TextField targetPathTextField;
    @FXML
    private TextArea consolleTextArea;

    private Model model;
    private Stage stage;

    private enum RadioButtonStates {
        file, directory
    };
    
    private RadioButtonStates currentRadioButtonState = RadioButtonStates.file;

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void radioButtonFile(ActionEvent event) {
        currentRadioButtonState = RadioButtonStates.file;
        consolleTextArea.appendText(" - radioButtonFile: " + currentRadioButtonState + "\n");
    }

    @FXML
    private void radioButtonDirectory(ActionEvent event) {
        currentRadioButtonState = RadioButtonStates.directory;
        consolleTextArea.appendText(" - radioButtonDirectory: " + currentRadioButtonState + "\n");
    }

    @FXML
    private void chooseSourcePath(ActionEvent event) {
        File sourcePathChoosen = null;
        switch (currentRadioButtonState) {
            case file:
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                sourcePathChoosen = fileChooser.showOpenDialog(stage);
                break;
            case directory:
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Open Resource File");
                sourcePathChoosen = directoryChooser.showDialog(stage);
                break;
        }
        if(sourcePathChoosen!=null) sourcePathTextField.setText(sourcePathChoosen.getAbsolutePath());
            
        /*String sourcePath = sourcePathTextField.getText().trim();
         if (sourcePath != null && !sourcePath.equals("")) {
         consolleTextArea.appendText(" - chooseSourcePath: " + sourcePath + "\n");
         } else {
         consolleTextArea.appendText(" - sourcePath null or empty!!!\n");
         }*/
    }

    @FXML
    private void chooseTargetPath(ActionEvent event) {
        File targetPathChoosen = null;
        switch (currentRadioButtonState) {
            case file:
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                targetPathChoosen = fileChooser.showOpenDialog(stage);
            break;
            case directory:
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Open Resource File");
                targetPathChoosen = directoryChooser.showDialog(stage);
            break;
        }
        if(targetPathChoosen!=null) targetPathTextField.setText(targetPathChoosen.getAbsolutePath());
        
        /*String targetPath = targetPathTextField.getText().trim();
        if (targetPath != null && !targetPath.equals("")) {
            consolleTextArea.appendText(" - chooseTargetPath: " + targetPath + "\n");
        } else {
            consolleTextArea.appendText(" - targetPath null or empty!!!\n");
        }*/
    }

    @FXML
    private void startCopy(ActionEvent event) {
        String sourcePathChoosen = sourcePathTextField.getText().trim();
        String targetPathChoosen = targetPathTextField.getText().trim();

        model.copy(new File(sourcePathChoosen), new File(targetPathChoosen));

        consolleTextArea.appendText(" - startCopy: " + sourcePathChoosen + " -> " + targetPathChoosen + "\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
