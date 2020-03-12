package Battleship.view.settingsscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import Battleship.view.aboutscreen.AboutScreenPresenter;
import Battleship.view.aboutscreen.AboutScreenView;
import Battleship.view.highscoresscreen.HighscoresScreenPresenter;
import Battleship.view.highscoresscreen.HighscoresScreenView;
import Battleship.view.infoscreen.InfoScreenPresenter;
import Battleship.view.infoscreen.InfoScreenView;
import Battleship.view.preparegamescreen.PrepareGameScreenPresenter;
import Battleship.view.preparegamescreen.PrepareGameScreenView;
import Battleship.view.universescreen.UniverseScreenPresenter;
import Battleship.view.universescreen.UniverseScreenView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;
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
        view.getCurrentGridSizeValueLabel().setText(Integer.toString(model.getGridSize()));
        view.getFleetSizeSlider().setMax(model.getMAX_FLEET_SIZE());
        view.getFleetSizeSlider().setMin(model.getMIN_FLEET_SIZE());
        view.getFleetSizeSlider().setValue(model.getNumberOfShips());
        view.getCurrentFleetSizeValueLabel().setText(Integer.toString(model.getNumberOfShips()));
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
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                UISettings.getCloseAlert(event, view.getScene()); }});
    }
}