package com.matteoveroni.control;

import com.matteoveroni.model.Model;
import javafx.stage.Stage;

/**
 * @author Matteo Veroni
 */
public interface ScreenSettable {

    public void setModel(Model model);

    public void setStage(Stage stage);
}
