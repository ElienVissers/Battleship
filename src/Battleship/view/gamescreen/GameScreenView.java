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

    private Label firstPlayerLabel;
    private Label secondPlayerLabel;
    private Label firstSunkenStatsLabel;
    private Label secondSunkenStatsLabel;
    private GridPane firstGrid;
    private GridPane secondGrid;
    private UISettings uiSettings;

    public GameScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.firstPlayerLabel = new Label();
        this.secondPlayerLabel = new Label();
        this.firstSunkenStatsLabel = new Label();
        this.secondSunkenStatsLabel = new Label();
        this.firstGrid = new GridPane();
        this.secondGrid = new GridPane();
    }

    private void layoutNodes() {
        layoutCenter();
    }

    private void layoutCenter() {
        layoutPlayerLabel(firstPlayerLabel, "red");
        layoutPlayerLabel(secondPlayerLabel, "blue");
        layoutStatsLabel(firstSunkenStatsLabel);
        layoutStatsLabel(secondSunkenStatsLabel);
        VBox first = new VBox();
        VBox second = new VBox();
        layoutVBox(first);
        layoutVBox(second);
        first.getChildren().addAll(firstPlayerLabel, firstGrid, firstSunkenStatsLabel);
        second.getChildren().addAll(secondPlayerLabel, secondGrid, secondSunkenStatsLabel);
        HBox content = new HBox();
        layoutHBox(content);
        content.getChildren().addAll(first, second);
    }

    private void layoutPlayerLabel(Label label, String color) {
        label.setPrefHeight(150);
        label.getStyleClass().add("title");
        if (color.equals("red")) {
            label.getStyleClass().add("red-text");
        } else if (color.equals("blue")) {
            label.getStyleClass().add("blue-text");
        }
        label.setPadding(new Insets(0, 0, 50, 50));
        setAlignment(label, Pos.CENTER);
    }

    private void layoutStatsLabel(Label label) {
        label.setPrefHeight(150);
        label.getStyleClass().add("stats-label");
        label.setPadding(new Insets(0, 0, 0, 50));
        setAlignment(label, Pos.CENTER);
    }

    private void layoutVBox(VBox box) {
        box.setAlignment(Pos.CENTER);
    }

    private void layoutHBox(HBox box) {
        box.setSpacing(uiSettings.getLowestRes()/3.0);
        box.setPadding(new Insets(100, 150, 100, 100));
        box.setAlignment(Pos.CENTER);
        setAlignment(box, Pos.CENTER);
        setCenter(box);
    }

    Label getFirstPlayerLabel() {
        return firstPlayerLabel;
    }

    Label getSecondPlayerLabel() {
        return secondPlayerLabel;
    }

    GridPane getFirstGrid() { return firstGrid; }

    GridPane getSecondGrid() { return secondGrid; }

    Label getFirstSunkenStatsLabel() {
        return firstSunkenStatsLabel;
    }

    Label getSecondSunkenStatsLabel() {
        return secondSunkenStatsLabel;
    }
}
