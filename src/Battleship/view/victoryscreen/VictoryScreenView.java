package Battleship.view.victoryscreen;

import Battleship.view.UISettings;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 15.02.2020 12:35
 */
public class VictoryScreenView extends BorderPane {

    private UISettings uiSettings;
    private Button saveButton;

    public VictoryScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.saveButton = new Button("SAVE");
    }

    private void layoutNodes() {
    }

    Button getSaveButton() {return saveButton;}
}
