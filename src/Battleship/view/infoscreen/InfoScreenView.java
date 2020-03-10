package Battleship.view.infoscreen;

import Battleship.view.UISettings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class InfoScreenView extends ScrollPane{

    private UISettings uiSettings;
    private Label infoText;

    public InfoScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        infoText = new Label();
    }

    private void layoutNodes() {
        infoText.getStyleClass().add("info-label");
        infoText.setPadding(new Insets(uiSettings.getInsetsMargin()));
        setContent(infoText);
    }

    Label getInfoText () {return infoText;}

}
