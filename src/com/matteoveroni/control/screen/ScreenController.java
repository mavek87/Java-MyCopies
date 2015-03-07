package com.matteoveroni.control.screen;

import com.matteoveroni.model.Model;
import javafx.stage.Stage;

/**
 * @author Matteo Veroni
 */
public interface ScreenController {

    public void setModel(Model model);

    public void setStage(Stage stage);
}
