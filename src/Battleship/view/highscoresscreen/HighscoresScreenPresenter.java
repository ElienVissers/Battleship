package Battleship.view.highscoresscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import Battleship.view.mainoptionsscreen.MainOptionsScreenView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Paths;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 15.02.2020 14:03
 */
public class HighscoresScreenPresenter {

    private BattleshipModel model;
    private HighscoresScreenView view;
    private UISettings uiSettings;

    public HighscoresScreenPresenter(BattleshipModel model, HighscoresScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        updateView();
        EventHandlers();
    }

    private void updateView() {
    }

    private void EventHandlers() {
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {handleCloseEvent(event);}});
    }

    private void handleCloseEvent(Event event){
        view.getScene().getWindow().hide();
    }
}
