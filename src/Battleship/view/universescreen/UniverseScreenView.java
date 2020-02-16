package Battleship.view.universescreen;

import Battleship.view.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class UniverseScreenView extends BorderPane  {

    private UISettings uiSettings;
    private MenuItem exitMI;
    private TextField cssName;
    private Button cssButton;
    private Button okButton;

    public UniverseScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.cssButton = new Button("Select File");
        this.cssName = new TextField();
        this.cssName.setPrefWidth(uiSettings.getLowestRes() / 3.0);
        this.cssName.setText(uiSettings.getStyleSheetPath().toString());
        this.okButton = new Button("OK");
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File");
        menuFile.getItems().addAll(exitMI);
        MenuBar menuBar = new MenuBar(menuFile);
        setTop(menuBar);
        HBox cssSettings = new HBox();
        cssSettings.setSpacing(uiSettings.getLowestRes() / 100.0);
        cssSettings.setPadding(new Insets(20));
        Label cssLabel = new Label("Style Sheet File Name:");
        cssSettings.getChildren().addAll(cssLabel, cssName, cssButton);
        this.setCenter(cssSettings);
        okButton.setAlignment(Pos.BOTTOM_RIGHT);
        this.setBottom(okButton);
    }

    MenuItem getExitItem() {return exitMI;}
    Button getCssButton () {return cssButton;}
    TextField getCssName () {return cssName;}
    Button getOkButton () {return okButton;}
}
