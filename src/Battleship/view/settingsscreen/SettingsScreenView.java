package Battleship.view.settingsscreen;

import Battleship.model.BattleshipModel;
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
    private BorderPane mainBP;
    private BorderPane gridSettingsBP;
    private BorderPane fleetSettingsBP;
    private BorderPane settingsBP;
    private Label gridSizeLabel;
    private Label fleetSizeLabel;
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
        this.fleetSizeSlider = new Slider(BattleshipModel.getMinFleetSize(), BattleshipModel.getMaxFleetSize(), 10);
        this.gridSizeSlider = new Slider(BattleshipModel.getMinGridSize(), BattleshipModel.getMaxGridSize(), 10);
        this.confirmButton = new Button("CONFIRM");
        this.cancelButton = new Button("CANCEL");
        BorderPane main = new BorderPane();
        BorderPane gridSettingsBP = new BorderPane();
        BorderPane fleetSettingsBP = new BorderPane();
        BorderPane settingsBP = new BorderPane();
    }

    private void layoutNodes() {

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
