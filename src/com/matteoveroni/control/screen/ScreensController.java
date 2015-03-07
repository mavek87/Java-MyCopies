package com.matteoveroni.control.screen;

import com.matteoveroni.FXMLMainLoader;
import com.matteoveroni.model.Model;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matteo Veroni
 */
public class ScreensController extends StackPane implements ScreenController {

    private final Map<String, Node> screens = new HashMap<>();
    private Model model;
    private Stage stage;

    private static final Logger LOG = LoggerFactory.getLogger(ScreensController.class);

    public ScreensController() {
        super();
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void addScreen(String name, Node rootNodeOfTheScreensSceneGraph) {
        screens.put(name, rootNodeOfTheScreensSceneGraph);
    }

    public boolean loadScreen(String name, String resource) {

        try {
            FXMLLoader myLoader = FXMLMainLoader.getInstance().loadResource(resource);
            
            Parent screenLoaded = (Parent) myLoader.load();
            
            ControllableScreen controllableScreenController = ((ControllableScreen) myLoader.getController());
            controllableScreenController.setScreensController(this);

            ScreenController screenController = ((ScreenController) myLoader.getController());
            screenController.setModel(model);
            screenController.setStage(stage);

            addScreen(name, screenLoaded);
            
            LOG.info("Screen " + name + " loaded succesfully!");
            return true;

        } catch (Exception e) {

            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean setScreen(final String name) {

        if (screens.get(name) != null) { //screen loaded
            final DoubleProperty opacity = opacityProperty();

            //Is there is more than one screen
            if (!getChildren().isEmpty()) {
                Timeline fade = new Timeline(
                    new KeyFrame(Duration.ZERO,
                        new KeyValue(opacity, 1.0)),
                    new KeyFrame(new Duration(200),
                        new EventHandler() {

                            @Override
                            public void handle(Event t) {
                                //remove displayed screen
                                getChildren().remove(0);
                                //add new screen
                                getChildren().add(0, screens.get(name));
                                Timeline fadeIn = new Timeline(
                                    new KeyFrame(Duration.ZERO,
                                        new KeyValue(opacity, 0.0)),
                                    new KeyFrame(new Duration(400),
                                        new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }

                        }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                //no one else been displayed, then just show
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.ZERO,
                        new KeyValue(opacity, 0.0)),
                    new KeyFrame(new Duration(400),
                        new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            
            LOG.info("Screen " + name + " switched succesfully!");
            return true;
        } else {
            LOG.warn("Screen hasn\'t been loaded!");
            return false;
        }
    }

    public Node getScreen(String name) {
        return screens.get(name);
    }

    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            LOG.warn("Screen didn\'t exist!");
            return false;
        } else {
            return true;
        }
    }
}
