package Battleship.view.infoscreen;

import Battleship.view.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class InfoScreenView extends BorderPane{

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
        setCenter(infoText);
        setPadding(new Insets(uiSettings.getInsetsMargin()));
    }

    Label getInfoText () {return infoText;}
}
