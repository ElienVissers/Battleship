package Battleship.view.gamescreen;

import Battleship.view.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 29.02.2020 10:19
 */

public class GameScreenView extends BorderPane {

    private Label activePlayerLabel;
    private Label sunkenStatsLabel;
    private GridPane activeGrid;
    private GridPane passiveGrid;
    private UISettings uiSettings;

    public GameScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.activePlayerLabel = new Label();
        this.sunkenStatsLabel = new Label();
        this.activeGrid = new GridPane();
        this.passiveGrid = new GridPane();
    }

    private void layoutNodes() {
        layoutTop();
        layoutCenter();
        layoutBottom();
    }

    private void layoutTop() {
        activePlayerLabel.setPrefHeight(150);
        setTop(activePlayerLabel);
        setAlignment(activePlayerLabel, Pos.CENTER);
    }

    private void layoutCenter() {
        HBox content = new HBox();
        content.getChildren().addAll(activeGrid, passiveGrid);
        content.setSpacing(300);
        content.setPadding(new Insets(100));
        content.setAlignment(Pos.CENTER);
        setCenter(content);
        setAlignment(content, Pos.CENTER);
    }

    private void layoutBottom() {
        sunkenStatsLabel.setPrefHeight(150);
        sunkenStatsLabel.getStyleClass().add("stats-label");
        setBottom(sunkenStatsLabel);
        setAlignment(sunkenStatsLabel, Pos.CENTER);
    }

    Label getActivePlayerLabel() {
        return activePlayerLabel;
    }

    GridPane getActiveGrid() { return activeGrid; }

    GridPane getPassiveGrid() { return passiveGrid; }

    public Label getSunkenStatsLabel() {
        return sunkenStatsLabel;
    }
}
