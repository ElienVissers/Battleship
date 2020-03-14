package Battleship.view.settingsscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

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
        view.getGridSizeSlider().getValue();
        view.getFleetSizeSlider().getValue();
        view.getFleetSizeSlider();
        view.getGridSizeSlider();

    }

    private void addEventHandlers() {
        // Koppelt event handlers (anon. inner klassen)
        // aan de controls uit de view.
        // Event handlers: roepen methodes aan uit het
        // model en zorgen voor een update van de view.
        view.getConfirmButton();
        view.getCancelButton();
    }

    //TODO Add warning on close
    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { UISettings.getCloseAlert(event, view.getScene()); }
        });
    }

}