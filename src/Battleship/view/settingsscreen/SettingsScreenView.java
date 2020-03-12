package Battleship.view.settingsscreen;

import Battleship.view.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:08
 */

public class SettingsScreenView extends VBox {
    private BorderPane gridSettingsBP;
    private BorderPane fleetSettingsBP;
    private BorderPane settingsBP;
    private HBox buttonsHB;
    private Label gridSizeLabel;
    private Label fleetSizeLabel;
    private Label currentGridSizeValueLabel;
    private Label currentFleetSizeValueLabel;
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
        this.fleetSizeSlider = new Slider();
        this.gridSizeSlider = new Slider();
        this.currentFleetSizeValueLabel = new Label();
        this.currentGridSizeValueLabel = new Label();
        this.confirmButton = new Button("CONFIRM");
        this.cancelButton = new Button("CANCEL");
        this.gridSettingsBP = new BorderPane();
        this.fleetSettingsBP = new BorderPane();
        this.buttonsHB = new HBox();
    }

    private void layoutNodes() {
        this.gridSettingsBP = layoutSliderSettings(gridSizeLabel, currentGridSizeValueLabel, gridSizeSlider);
        this.fleetSettingsBP = layoutSliderSettings(fleetSizeLabel, currentFleetSizeValueLabel, fleetSizeSlider);
        this.buttonsHB = layoutHBox(confirmButton, cancelButton);
        this.getChildren().addAll(gridSettingsBP, fleetSettingsBP, buttonsHB);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(30));
        this.setSpacing(30);
    }

    private HBox layoutHBox(Button child1, Button child2) {
        HBox box = new HBox();
        box.getChildren().addAll(child1, child2);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30));
        box.setSpacing(30);
        return box;
    }

    private BorderPane layoutSliderSettings(Label settingsLabel, Label currentValueLabel, Slider slider){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(settingsLabel);
        settingsLabel.getStyleClass().add("title");
        borderPane.setLeft(slider);
        borderPane.setRight(currentValueLabel);
        return borderPane;
    }

    Button getConfirmButton() {
        return confirmButton;
    }

    Button getCancelButton() {
        return cancelButton;
    }

    Slider getGridSizeSlider() {
        return gridSizeSlider;
    }

    Slider getFleetSizeSlider() {
        return fleetSizeSlider;
    }

    public Label getCurrentGridSizeValueLabel() {
        return currentGridSizeValueLabel;
    }

    public Label getCurrentFleetSizeValueLabel() {
        return currentFleetSizeValueLabel;
    }
}
