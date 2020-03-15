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
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:08
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
        updateView();
        addEventHandlers();
    }

    private void updateView() {
    }

    private void addEventHandlers() {
        // Koppelt event handlers (anon. inner klassen)
        // aan de controls uit de view.
        // Event handlers: roepen methodes aan uit het
        // model en zorgen voor een update van de view.
        //TODO vragen over alert 2de if methode
        view.getConfirmButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(view.getFleetSizeSlider().getValue() >= view.getGridSizeSlider().getValue()) {
                    final Alert stopWindow = new Alert(Alert.AlertType.ERROR);
                    try {
                        DialogPane dialogPane = stopWindow.getDialogPane();
                        dialogPane.setGraphic(new Label());
                        dialogPane.getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                        dialogPane.getStyleClass().add("alert-window");
                    } catch (Exception ignored) {}
                    if (Files.exists(uiSettings.getApplicationIconPath())) {
                        try {
                            Stage stage = (Stage) stopWindow.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                        } catch (Exception ignored) {}
                    }
                    stopWindow.setTitle("Warning!");
                    stopWindow.setHeaderText("The selected fleet size is bigger than your universe (Grid) supports");
                    stopWindow.setContentText("The fleet size has been adjust to fit in your universe");
                    stopWindow.showAndWait();
                    event.consume();
                    model.setNumberOfShips((int) view.getGridSizeSlider().getValue());
                }
                model.setGridSize((int) view.getGridSizeSlider().getValue());
                model.setNumberOfShips((int) view.getFleetSizeSlider().getValue());
            }
        });
        //TODO add error message same as on close pane (That shows that settings won't be changed. Are u sure you want to leave? Yes? No?) close view on yes
        view.getCancelButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                event.consume();
            }
            });
    }

    //TODO same as above in view.getCancelButton().setOnAction()
    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { UISettings.getCloseAlert(event, view.getScene()); }
        });
    }

}