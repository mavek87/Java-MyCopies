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

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private void chooseSourcePath(ActionEvent event) {
        String sourcePath = sourcePathTextField.getText().trim();
        if (sourcePath != null && !sourcePath.equals("")) {
            consolleTextArea.appendText(" - chooseSourcePath: " + sourcePath + "\n");
        } else {
            consolleTextArea.appendText(" - sourcePath null or empty!!!\n");
        }
    }

    @FXML
    private void chooseTargetPath(ActionEvent event) {
        String targetPath = targetPathTextField.getText().trim();
        if (targetPath != null && !targetPath.equals("")) {
            consolleTextArea.appendText(" - chooseTargetPath: " + targetPath + "\n");
        } else {
            consolleTextArea.appendText(" - targetPath null or empty!!!\n");
        }
    }

    @FXML
    private void startCopy(ActionEvent event) {
        String sourcePath = sourcePathTextField.getText().trim();
        String targetPath = targetPathTextField.getText().trim();

        model.copy(new File(sourcePath), new File(targetPath));

        consolleTextArea.appendText(" - startCopy: " + sourcePath + " -> " + targetPath + "\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
