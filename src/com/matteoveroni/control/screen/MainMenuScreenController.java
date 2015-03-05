package com.matteoveroni.control.screen;

import com.matteoveroni.model.Model;
import java.io.File;
import java.io.IOException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
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

    private static final Logger LOG = LoggerFactory.getLogger(MainMenuScreenController.class);

    private enum RadioButtonStates {

        file, directory
    };
    private RadioButtonStates isRadioButtonFileOrDirectory = RadioButtonStates.file;

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
        isRadioButtonFileOrDirectory = RadioButtonStates.file;
        consolleTextArea.appendText(" - radioButtonFile: " + isRadioButtonFileOrDirectory + "\n");
        LOG.info("radioButtonFile: " + isRadioButtonFileOrDirectory);
    }

    @FXML
    private void radioButtonDirectory(ActionEvent event) {
        isRadioButtonFileOrDirectory = RadioButtonStates.directory;
        consolleTextArea.appendText(" - radioButtonDirectory: " + isRadioButtonFileOrDirectory + "\n");
        LOG.info("radioButtonDirectory: " + isRadioButtonFileOrDirectory);
    }

    @FXML
    private void chooseSourcePath(ActionEvent event) {
        File sourcePathChoosen = chooseFileOrDirectory();
        if (sourcePathChoosen != null && !sourcePathChoosen.toString().trim().equals("")) {
            sourcePathTextField.setText(sourcePathChoosen.getAbsolutePath());
            consolleTextArea.appendText(" - sourcePathChoosen: " + sourcePathChoosen + "\n");
            LOG.info("sourcePathChoosen: " + sourcePathChoosen);
        }
    }

    @FXML
    private void chooseTargetPath(ActionEvent event) {
        File targetPathChoosen = chooseFileOrDirectory();
        if (targetPathChoosen != null && !targetPathChoosen.toString().trim().equals("")) {
            targetPathTextField.setText(targetPathChoosen.getAbsolutePath());
            consolleTextArea.appendText(" - targetPathChoosen: " + targetPathChoosen + "\n");
            LOG.info("targetPathChoosen: " + targetPathChoosen);
        }
    }

    private File chooseFileOrDirectory() {
        File pathChoosen = null;
        switch (isRadioButtonFileOrDirectory) {
            case file:
                pathChoosen = chooseFile();
                break;
            case directory:
                pathChoosen = chooseDirectory();
                break;
        }
        return pathChoosen;
    }

    private File chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        return fileChooser.showOpenDialog(stage);
    }

    private File chooseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Resource File");
        return directoryChooser.showDialog(stage);
    }

    @FXML
    private void startCopy(ActionEvent event) {
        String sourcePathChoosen = sourcePathTextField.getText().trim();
        String targetPathChoosen = targetPathTextField.getText().trim();

        try {
            model.simpleCopy(new File(sourcePathChoosen), new File(targetPathChoosen));
            consolleTextArea.appendText(" - startCopy: " + sourcePathChoosen + " -> " + targetPathChoosen + "\n");
            LOG.info("startCopy: " + sourcePathChoosen + " -> " + targetPathChoosen);
        } catch (IOException ex) {
            consolleTextArea.appendText("copy error: " + ex.getMessage() + "\n");
            LOG.error(ex.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
