package Battleship.view.victoryscreen;

import Battleship.view.UISettings;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 15.02.2020 12:35
 */
public class VictoryScreenView extends BorderPane {

    private UISettings uiSettings;
    private Label victoryLabel;
    private Button saveButton;

    private ImageView animatedShip1;
    private ImageView animatedShip2;
    private ImageView animatedShip3;
    private HBox animatedShipBox;

    public VictoryScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
        animate();
    }

    private void initialiseNodes() {
        this.victoryLabel = new Label();
        this.saveButton = new Button("SAVE GAME");
        this.animatedShip1 = new ImageView();
        this.animatedShip2 = new ImageView();
        this.animatedShip3 = new ImageView();
        this.animatedShipBox = new HBox();
    }

    private void layoutNodes() {
        VBox content = new VBox();
        content.getChildren().addAll(victoryLabel, saveButton);
        content.setSpacing(100);
        content.setAlignment(Pos.CENTER);
        setCenter(content);
        addAnimation();
    }

    Label getVictoryLabel() { return victoryLabel; }

    Button getSaveButton() {return saveButton;}

    ImageView getAnimatedShip1() {
        return animatedShip1;
    }

    ImageView getAnimatedShip2() {
        return animatedShip2;
    }

    ImageView getAnimatedShip3() {
        return animatedShip3;
    }

    private void addAnimation() {
        animatedShip1.setTranslateX(-350);
        animatedShip2.setTranslateX(-550);
        animatedShip3.setTranslateX(-800);
        animatedShipBox.getChildren().addAll(animatedShip1, animatedShip2, animatedShip3);
        animatedShipBox.setTranslateX(-1000);
        animatedShipBox.setSpacing(500);
        animatedShipBox.setPadding(new Insets(0, 0, 50, 0));
        setBottom(animatedShipBox);
    }

    private void animate() {
        TranslateTransition fly = new TranslateTransition();
        fly.setNode(animatedShipBox);
        fly.setDuration(Duration.seconds(20));
        fly.setByX(uiSettings.getResX() + 1000);
        fly.setCycleCount(Timeline.INDEFINITE);
        fly.setInterpolator(Interpolator.LINEAR);
        fly.play();
    }
}