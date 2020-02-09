package Battleship;

import Battleship.view.startscreen.*;
import Battleship.model.*;
import Battleship.view.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import java.net.MalformedURLException;
import java.nio.file.Files;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:00
 * RUN/DEBUG configuration - vm options: --module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        UISettings uiSettings = new UISettings();
        BattleshipModel model = new BattleshipModel();
        StartScreenView view = new StartScreenView(uiSettings);
        StartScreenPresenter presenter = new StartScreenPresenter(model, view, uiSettings);
        Scene scene = new Scene(view);
        if (uiSettings.styleSheetAvailable()){
            try {
                scene.getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
            } catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        }
        primaryStage.setScene(scene);
        primaryStage.setHeight(uiSettings.getLowestRes() / 4);
        primaryStage.setWidth(uiSettings.getLowestRes() / 4);
        primaryStage.setTitle(uiSettings.getApplicationName());
        if (Files.exists(uiSettings.getApplicationIconPath())) {
            try {
                primaryStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
            }
            catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        } else { // do nothing, if ApplicationIcon is not available, program can continue
        }
        presenter.windowsHandler();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
