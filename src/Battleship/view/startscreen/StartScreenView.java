package Battleship.view.startscreen;

import Battleship.view.UISettings;
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
        int ImageSize = uiSettings.getLowestRes()/2;
        timeProgress.setStyle("-fx-accent: #2D2D2D");
        timeDisplay.setStyle("-fx-text-fill: ivory;");
        VBox time = new VBox();
        time.getChildren().addAll(timeDisplay, timeProgress);
        time.setAlignment(Pos.CENTER);
        time.setSpacing(10);
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
        this.getChildren().addAll(centralImage, time);
        this.setSpacing(60);
        this.setAlignment(Pos.CENTER);
    }

    Label getTimeDisplay () {return (timeDisplay);}

    ProgressBar getTimeProgress () {return (timeProgress);}

    ImageView getCentralImage() {return (centralImage);}

    StartScreenTransition getTransition() {return trans;}

    private void animate() {
        trans = new StartScreenTransition(this,5);
        trans.play();
    }

}
