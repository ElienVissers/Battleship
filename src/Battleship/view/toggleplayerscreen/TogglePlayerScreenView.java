package Battleship.view.toggleplayerscreen;

import Battleship.view.UISettings;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;

/**
 * The View class of the TogglePlayerScreen.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020
 */

public class TogglePlayerScreenView extends VBox  {

    private Label playerLabel;
    private Button startButton;
    private UISettings uiSettings;

    public TogglePlayerScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.playerLabel = new Label();
        this.playerLabel.getStyleClass().add("title");
        this.startButton = new Button("START");
    }

    private void layoutNodes() {
        this.startButton.setAlignment(Pos.CENTER);
        this.getChildren().addAll(playerLabel, startButton);
        this.setSpacing(100);
        this.setAlignment(Pos.CENTER);
    }

    Label getPlayerLabel() {return playerLabel;}

    Button getStartButton() {return startButton;}

}
