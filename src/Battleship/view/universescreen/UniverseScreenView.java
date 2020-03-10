package Battleship.view.universescreen;

import Battleship.view.UISettings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class UniverseScreenView extends BorderPane  {

    private UISettings uiSettings;

    private Label gridsizeLabel;
    private Label numberofshipsLabel;
    private TextField gridsizeTextField;
    private TextField numberofshipsTextField;
    private Button saveButton;

    public UniverseScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.gridsizeLabel = new Label("Choose the size of your battlefield (7 - 15): ");
        this.gridsizeLabel.getStyleClass().add("title");
        this.numberofshipsLabel = new Label("Choose the size of your fleet (4 - 15):");
        this.numberofshipsLabel.getStyleClass().add("title");
        this.gridsizeTextField = new TextField("10");
        this.numberofshipsTextField = new TextField("10");
        this.saveButton = new Button("SAVE");
    }

    private void layoutNodes() {
        HBox gridsizeBox = layoutHBox(gridsizeLabel, gridsizeTextField);
        HBox numberofshipsBox = layoutHBox(numberofshipsLabel, numberofshipsTextField);
        VBox content = layoutVBox(gridsizeBox, numberofshipsBox, saveButton);
        this.setCenter(content);
    }

    private HBox layoutHBox(Node child1, Node child2) {
        HBox box = new HBox();
        box.getChildren().addAll(child1, child2);
        box.setSpacing(10);
        return box;
    }

    private VBox layoutVBox(Node child1, Node child2, Node child3) {
        VBox box = new VBox();
        box.getChildren().addAll(child1, child2, child3);
        box.setPadding(new Insets(30));
        box.setSpacing(30);
        return box;
    }

    TextField getGridsizeTextField() {
        return gridsizeTextField;
    }

    TextField getNumberofshipsTextField() {
        return numberofshipsTextField;
    }
}
