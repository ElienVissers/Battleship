package Battleship.view.settingsscreen;

import Battleship.view.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:08
 */

public class SettingsScreenView extends VBox {
    private VBox gridSettingsVB;
    private VBox fleetSettingsVB;
    private BorderPane settingsBP;
    private HBox buttonsHB;
    private Label gridSizeLabel;
    private Label fleetSizeLabel;
    private Slider gridSizeSlider;
    private Slider fleetSizeSlider;
    private final Double MAX_INCREMENT_SIZE = 1.0;
    private final int MINOR_INCREMENT_SIZE = 0;
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
        this.confirmButton = new Button("CONFIRM");
        this.cancelButton = new Button("CANCEL");
        this.gridSettingsVB = new VBox();
        this.fleetSettingsVB = new VBox();
        this.buttonsHB = new HBox();
    }

    private void layoutNodes() {
        this.gridSettingsVB = layoutSliderSettings(gridSizeLabel, gridSizeSlider);
        this.fleetSettingsVB = layoutSliderSettings(fleetSizeLabel, fleetSizeSlider);
        this.buttonsHB = layoutHBox(confirmButton, cancelButton);
        this.getChildren().addAll(gridSettingsVB, fleetSettingsVB, buttonsHB);
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

    private VBox layoutSliderSettings(Label settingsLabel, Slider slider){
        VBox alignment = new VBox();
        slider.setMaxWidth((uiSettings.getResX()/3));
        alignment.getChildren().addAll(settingsLabel, slider);
        alignment.setAlignment(Pos.CENTER);
        settingsLabel.getStyleClass().add("title");
        alignment.setPadding(new Insets(10));
        alignment.setSpacing(30);
        return alignment;
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

}
