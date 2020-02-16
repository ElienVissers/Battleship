package Battleship.view.preparegamescreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 15.02.2020 17:54
 */
public class PrepareGameScreenPresenter {

    private BattleshipModel model;
    private PrepareGameScreenView view;
    private UISettings uiSettings;

    public PrepareGameScreenPresenter(BattleshipModel model, PrepareGameScreenView view, UISettings uiSettings) {
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

    public void windowsHandlers() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { UISettings.getCloseAlert(event, view.getScene()); }});
    }

}
