package Battleship.view.preparegamescreen;

import Battleship.view.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 15.02.2020 17:54
 */

public class PrepareGameScreenView extends BorderPane {

    private Label activePlayerLabel;
    private Label shipLabel2;
    private Label shipLabel3;
    private Label shipLabel4;
    private Label shipLabel5;
    private GridPane grid;
    private Button rotateButton;
    private Button doneButton;
    private UISettings uiSettings;

    public PrepareGameScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.activePlayerLabel = new Label();
        this.shipLabel2 = new Label();
        this.shipLabel3 = new Label();
        this.shipLabel4 = new Label();
        this.shipLabel5 = new Label();
        this.grid = new GridPane();
        this.rotateButton = new Button("HORIZONTAL");
        this.doneButton = new Button("DONE");
    }

    private void layoutNodes() {
        layoutTop();
        layoutCenter();
    }

    private void layoutTop() {
        activePlayerLabel.setPrefHeight(200);
        setTop(activePlayerLabel);
        setAlignment(activePlayerLabel, Pos.CENTER);
    }

    private void layoutCenter() {
        HBox content = new HBox();
        content.getChildren().addAll(createShipBox(), grid, createButtonBox());
        content.setSpacing(100);
        content.setPadding(new Insets(100));
        content.setAlignment(Pos.CENTER);
        setCenter(content);
        setAlignment(content, Pos.CENTER);
    }

    private VBox createShipBox() {
        VBox shipBox = new VBox();
        layoutShipLabel(shipLabel2);
        layoutShipLabel(shipLabel3);
        layoutShipLabel(shipLabel4);
        layoutShipLabel(shipLabel5);
        Region empty = new Region();
        empty.setPrefHeight(50);
        shipBox.getChildren().addAll(empty, shipLabel2, shipLabel3, shipLabel4, shipLabel5);
        shipBox.setSpacing(50);
        shipBox.setPrefWidth(400);
        return shipBox;
    }

    private VBox createButtonBox() {
        VBox buttonBox = new VBox();
        Region empty = new Region();
        empty.setPrefHeight(50);
        doneButton.getStyleClass().add("button-disabled");
        buttonBox.getChildren().addAll(empty, rotateButton, doneButton);
        buttonBox.setSpacing(50);
        buttonBox.setPrefWidth(450);
        return buttonBox;
    }

    private void layoutShipLabel(Label label) {
        label.getStyleClass().add("ship-label");
        label.setContentDisplay(ContentDisplay.RIGHT);
    }

    Label getActivePlayerLabel() {
        return activePlayerLabel;
    }

    Label getShipLabel2() {
        return shipLabel2;
    }

    Label getShipLabel3() {
        return shipLabel3;
    }

    Label getShipLabel4() {
        return shipLabel4;
    }

    Label getShipLabel5() {
        return shipLabel5;
    }

    GridPane getGrid() { return grid; }

    Button getRotateButton() {
        return rotateButton;
    }

    Button getDoneButton() {
        return doneButton;
    }
}
