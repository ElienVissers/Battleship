package Battleship.view.preparegamescreen;

import Battleship.view.UISettings;
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
    }

    private void layoutNodes() {
        layoutTop();
        layoutCenter();
    }

    private void layoutTop() {
        activePlayerLabel.setPrefHeight(200);
        this.setTop(activePlayerLabel);
        this.setAlignment(activePlayerLabel, Pos.CENTER);
    }

    private void layoutCenter() {
        HBox content = new HBox();
        content.getChildren().addAll(createShipBox(), createGridPane());
        this.setCenter(content);
    }

    private VBox createShipBox() {
        VBox shipBox = new VBox();
        layoutShipLabel(shipLabel2);
        layoutShipLabel(shipLabel3);
        layoutShipLabel(shipLabel4);
        layoutShipLabel(shipLabel5);
        shipBox.getChildren().addAll(shipLabel2, shipLabel3, shipLabel4, shipLabel5);
        shipBox.setSpacing(50);
        return shipBox;
    }

    private void layoutShipLabel(Label label) {
        label.setContentDisplay(ContentDisplay.RIGHT);
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        //TODO get gridsize from Presenter (< Model)...
        for (int i = 0; i < 10; i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            grid.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 10; i++) {
            RowConstraints row = new RowConstraints(50);
            grid.getRowConstraints().add(row);
        }
        grid.setGridLinesVisible(true);
        return grid;
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
}
