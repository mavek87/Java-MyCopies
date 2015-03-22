package com.matteoveroni.control;

import com.matteoveroni.FXMLMainLoader;
import com.matteoveroni.control.screens.ScreenControllable;
import com.matteoveroni.control.screens.ScreenSettable;
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
public class ScreensController extends StackPane implements ScreenSettable {

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

    public void addScreen(String screenName, Node rootNodeOfTheScreensSceneGraph) {
        screens.put(screenName, rootNodeOfTheScreensSceneGraph);
    }

    public boolean loadScreen(String screenName, String resource) {

        try {
            FXMLLoader myLoader = FXMLMainLoader.getInstance().loadResource(resource);
            
            Parent screenLoaded = (Parent) myLoader.load();
            
            ScreenControllable controllableScreenController = ((ScreenControllable) myLoader.getController());
            controllableScreenController.setScreensController(this);

            ScreenSettable screenController = ((ScreenSettable) myLoader.getController());
            screenController.setModel(model);
            screenController.setStage(stage);

            addScreen(screenName, screenLoaded);
            
            LOG.info("Screen \'" + screenName + "\' loaded succesfully!");
            return true;

        } catch (Exception e) {

            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean setScreen(final String screenName) {

        //If screen loaded
        if (screens.get(screenName) != null) { 
            final DoubleProperty opacity = opacityProperty();

            //Is there more than one screen
            if (!getChildren().isEmpty()) {
                Timeline fade = new Timeline(
                    new KeyFrame(Duration.ZERO,
                        new KeyValue(opacity, 1.0)),
                    new KeyFrame(new Duration(200),
                        new EventHandler() {

                            @Override
                            public void handle(Event t) {
                                //Remove displayed screen
                                getChildren().remove(0);
                                //Add new screen
                                getChildren().add(0, screens.get(screenName));
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
                //No one else been displayed, then just show
                setOpacity(0.0);
                getChildren().add(screens.get(screenName));
                Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.ZERO,
                        new KeyValue(opacity, 0.0)),
                    new KeyFrame(new Duration(400),
                        new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            
            LOG.info("Screen \'" + screenName + "\' succesfully setted!");
            return true;
        } else {
            LOG.warn("Screen \'" + screenName + "\' hasn\'t been loaded!");
            return false;
        }
    }

    public Node getScreen(String screenName) {
        return screens.get(screenName);
    }

    public boolean unloadScreen(String screenName) {
        if (screens.remove(screenName) == null) {
            LOG.warn("Screen \'" + screenName + "\' didn\'t exists!");
            return false;
        } else {
            return true;
        }
    }
}
