package Battleship.view.universescreen;

import Battleship.model.*;
import Battleship.view.UISettings;
import javafx.event.*;
import javafx.stage.WindowEvent;

public class UniverseScreenPresenter {

    private BattleshipModel model;
    private UniverseScreenView view;
    private UISettings uiSettings;

    public UniverseScreenPresenter(BattleshipModel model, UniverseScreenView view, UISettings uiSettings) {
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
            public void handle(WindowEvent event) {
                UISettings.getCloseAlert(event, view.getScene()); }});
    }
}
