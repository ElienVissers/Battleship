package Battleship.view.aboutscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;

/**
 * The Presenter class of the AboutScreen.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 05.03.2020
 */
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
