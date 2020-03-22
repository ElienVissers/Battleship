package Battleship.view.settingsscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.nio.file.Files;

/**
 * The Presenter class of the SettingsScreen.
 *
 * @author Jan Dubois
 * @version 1.0 14.03.2020
 */

public class SettingsScreenPresenter {

    private BattleshipModel model;
    private SettingsScreenView view;
    private UISettings uiSettings;

    public SettingsScreenPresenter(BattleshipModel model, SettingsScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        view.getGridSizeSlider().setMax(model.getMAX_GRID_SIZE());
        view.getGridSizeSlider().setMin(model.getMIN_GRID_SIE());
        view.getGridSizeSlider().setValue(model.getGridSize());
        view.getFleetSizeSlider().setMax(model.getMAX_FLEET_SIZE());
        view.getFleetSizeSlider().setMin(model.getMIN_FLEET_SIZE());
        view.getFleetSizeSlider().setValue(model.getNumberOfShips());
        addEventHandlers();
    }

    private void updateView() {
        model.setGridSize((int) view.getGridSizeSlider().getValue());
        model.setNumberOfShips((int) view.getFleetSizeSlider().getValue());
        view.getGridSizeSlider().setValue(model.getGridSize());
    }

    private void addEventHandlers() {
        view.getConfirmButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(view.getFleetSizeSlider().getValue() > view.getGridSizeSlider().getValue()) {
                    final Alert stopWindow = new Alert(Alert.AlertType.ERROR);
                    try {
                        DialogPane dialogPane = stopWindow.getDialogPane();
                        dialogPane.setGraphic(new Label());
                        dialogPane.getStylesheets().add("/stylesheets/battleship_standard.css");
                        dialogPane.getStyleClass().add("alert-window");
                    } catch (Exception ignored) {}
                        try {
                            Stage stage = (Stage) stopWindow.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image("images/ApplicationIcon.png"));
                        } catch (Exception ignored) {}
                    stopWindow.setTitle("Warning!");
                    stopWindow.setHeaderText("The selected fleet size is bigger than your universe (Grid) supports");
                    stopWindow.setContentText("The fleet size has been adjust to fit in your universe");
                    stopWindow.showAndWait();
                    event.consume();
                    model.setNumberOfShips((int) view.getGridSizeSlider().getValue());
                    view.getFleetSizeSlider().setValue(model.getNumberOfShips());
                    updateView();
                }
                else{
                    updateView();
                    view.getScene().getWindow().hide();
                }
            }
        });

        view.getCancelButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { view.getScene().getWindow().hide(); }
            });
    }
}