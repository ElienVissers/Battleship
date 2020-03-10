package Battleship.view.aboutscreen;

import Battleship.view.UISettings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.MalformedURLException;
import java.nio.file.Files;

public class AboutScreenView extends BorderPane {

    private UISettings uiSettings;



    public AboutScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
    }

    private void layoutNodes() {
         if (Files.exists(uiSettings.getAboutImagePath())) {
            try {

                Image centralImage = new Image(uiSettings.getAboutImagePath().toUri().toURL().toString(), uiSettings.getLowestRes() /2.0, uiSettings.getLowestRes() /1.0, true, true);
                setCenter(new ImageView(centralImage));
            }
            catch (MalformedURLException ignored) { }
        }
        VBox labelBox = new VBox();
        labelBox.getChildren().addAll(new Label("game version 1.0 © Elien Vissers-Similon & Jan Dubois"),
                new Label(""),
                new Label("technical support by Wim De Keyser"),
                new Label("graphical support by Ivan Zilic"));
        labelBox.setAlignment(Pos.CENTER);
        setBottom(labelBox);
        setAlignment(labelBox, Pos.CENTER);
    }

}
