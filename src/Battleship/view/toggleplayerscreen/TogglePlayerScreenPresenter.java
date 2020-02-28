package Battleship.view.toggleplayerscreen;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 28.02.2020 13:22
 */

import Battleship.model.*;
import Battleship.view.UISettings;
import Battleship.view.preparegamescreen.PrepareGameScreenPresenter;
import Battleship.view.preparegamescreen.PrepareGameScreenView;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.WindowEvent;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:08
 */

public class TogglePlayerScreenPresenter {

    private BattleshipModel model;
    private TogglePlayerScreenView view;
    private UISettings uiSettings;

    public TogglePlayerScreenPresenter(BattleshipModel model, TogglePlayerScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;

        view.getPlayerLabel().setText(model.getActivePlayer().getName() + ", are you ready to make a move?");
        if (model.getActivePlayer().getColor().equals("red")) {
            view.getPlayerLabel().getStyleClass().add("red-text");
        } else if (model.getActivePlayer().getColor().equals("blue")) {
            view.getPlayerLabel().getStyleClass().add("blue-text");
        }

        updateView();
        EventHandlers();
    }

    private void updateView() {
    }

    private void EventHandlers() {
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO either open PrepareGameScreen or GameScreen
                PrepareGameScreenView prepareGameScreenView = new PrepareGameScreenView(uiSettings);
                PrepareGameScreenPresenter prepareGameScreenpresenter = new PrepareGameScreenPresenter(model, prepareGameScreenView, uiSettings);
                view.getScene().setRoot(prepareGameScreenView);
                prepareGameScreenpresenter.windowsHandlers();
            }});
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { UISettings.getCloseAlert(event, view.getScene()); }});
    }

}