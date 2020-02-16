package Battleship.view.aboutscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AboutScreenPresenter {

    private BattleshipModel model;
    private AboutScreenView view;
    private UISettings uiSettings;

    public AboutScreenPresenter(BattleshipModel model, AboutScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        view.getBtnOk().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getScene().getWindow().hide();
            }
        });
    }
}
