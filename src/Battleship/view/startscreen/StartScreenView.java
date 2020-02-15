package Battleship.view.startscreen;

import Battleship.view.UISettings;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.nio.file.Files;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:13
 */

public class StartScreenView extends VBox {

    private UISettings uiSettings;
    private Label timeDisplay;
    private ProgressBar timeProgress;
    private ImageView centralImage;
    private StartScreenTransition trans;

    public StartScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
        animate();
    }

    private void initialiseNodes() {
        this.timeDisplay = new Label("Loading: 0.0");
        this.timeProgress = new ProgressBar();
    }

    private void layoutNodes() {
        int ImageSize = uiSettings.getLowestRes()/8;
        timeProgress.setStyle("-fx-accent: red");
        if (Files.exists(uiSettings.getStartScreenImagePath())) {
            try {
                centralImage = new ImageView(new Image(uiSettings.getStartScreenImagePath().toUri().toURL().toString()));
                centralImage.setPreserveRatio(true);
                centralImage.setFitHeight(ImageSize);
                centralImage.setFitWidth(ImageSize);
                centralImage.setSmooth(true);
            }
            catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        }
        this.setPadding(new Insets(uiSettings.getInsetsMargin()));
        this.getChildren().addAll(centralImage, timeDisplay, timeProgress);
        this.setAlignment(Pos.CENTER);
    }

    Label getTimeDisplay () {return (timeDisplay);}

    ProgressBar getTimeProgress () {return (timeProgress);}

    ImageView getCentralImage() {return (centralImage);}

    StartScreenTransition getTransition() {return trans;}

    private void animate() {
        trans = new StartScreenTransition(this,4);
        trans.play();
    }

}
