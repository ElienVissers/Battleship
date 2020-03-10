package Battleship.view.aboutscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;

public class AboutScreenPresenter {

    private BattleshipModel model;
    private AboutScreenView view;
    private UISettings uiSettings;

    public AboutScreenPresenter(BattleshipModel model, AboutScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
    }
}
