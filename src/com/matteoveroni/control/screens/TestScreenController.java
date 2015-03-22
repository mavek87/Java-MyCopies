package com.matteoveroni.control.screens;

import com.matteoveroni.control.ScreensController;
import com.matteoveroni.model.Model;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Matteo Veroni
 */
public class TestScreenController implements ScreenSettable, ScreenControllable{

    private Model model;
    
    private Stage stage;
    
    private ScreensController myController;
    
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
    
    public void a(){
        myController.setScreen("");
    }

    
}