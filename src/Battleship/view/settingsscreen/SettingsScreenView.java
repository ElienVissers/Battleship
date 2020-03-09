package Battleship.view.settingsscreen;

import Battleship.view.UISettings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:08
 */

public class SettingsScreenView extends BorderPane {
    private BorderPane settingsBP;
    private BorderPane gridSettingsBP;
    private BorderPane fleetSettingsBP;
    private Label gridSizeLabel;
    private Label fleetSizeLabel;
    private int maxGridSize;
    private int minGridSize;
    private int maxFleetSize;
    private int minFleetSize;
    private Slider gridSizeSlider;
    private Slider fleetSizeSlider;
    private Button confirmButton;
    private Button cancelButton;
    private UISettings uiSettings;


    public SettingsScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //CONTENT
        this.gridSizeLabel = new Label("Grid Size");
        this.fleetSizeLabel = new Label("Fleet Size");
        this.fleetSizeSlider = new Slider(minFleetSize, maxFleetSize, 10);
        this.gridSizeSlider = new Slider(minGridSize, maxGridSize, 10);
        this.confirmButton = new Button("CONFIRM");
        this.cancelButton = new Button("CANCEL");
    }

    private void layoutNodes() {
    }

    Button getConfirmButton() {
        return confirmButton;
    }

    Button getCancelButton() {
        return cancelButton;
    }

    int getMaxGridSize() {
        return maxGridSize;
    }

    void setMaxGridSize(int maxGridSize) {
        this.maxGridSize = maxGridSize;
    }

    int getMinGridSize() {
        return minGridSize;
    }

    void setMinGridSize(int minGridSize) {
        this.minGridSize = minGridSize;
    }

    int getMaxFleetSize() {
        return maxFleetSize;
    }

    void setMaxFleetSize(int maxFleetSize) {
        this.maxFleetSize = maxFleetSize;
    }

    int getMinFleetSize() {
        return minFleetSize;
    }

    void setMinFleetSize(int minFleetSize) {
        this.minFleetSize = minFleetSize;
    }

    Slider getGridSizeSlider() {
        return gridSizeSlider;
    }

    void setGridSizeSlider(Slider gridSizeSlider) {
        this.gridSizeSlider = gridSizeSlider;
    }

    Slider getFleetSizeSlider() {
        return fleetSizeSlider;
    }

    void setFleetSizeSlider(Slider fleetSizeSlider) {
        this.fleetSizeSlider = fleetSizeSlider;
    }
}
