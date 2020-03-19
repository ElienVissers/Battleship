package Battleship.view.highscoresscreen;

import Battleship.view.UISettings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;


/**
 * The View class of the HighscoresScreen.
 *
 * @author Jan Dubois
 * @version 1.0 14.03.2020
 */
public class HighscoresScreenView extends ScrollPane {

    private UISettings uiSettings;
    private Label highscoresText;

    public HighscoresScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() { highscoresText = new Label(); }

    private void layoutNodes() {
        highscoresText.getStyleClass().add("info-label");
        highscoresText.setPadding(new Insets(uiSettings.getInsetsMargin()));
        setContent(highscoresText);
    }

    Label getHighscoresText() {return highscoresText;}
}
