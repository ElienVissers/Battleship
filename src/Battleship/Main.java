package Battleship;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import Battleship.view.startscreen.StartScreenPresenter;
import Battleship.view.startscreen.StartScreenView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.nio.file.Files;

/**
 * Main class of the Battleship application. Shows the primaryStage and sets StartScreen as the view.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020
 *
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
            } catch (MalformedURLException ignored) { }
        }
        primaryStage.setScene(scene);
        primaryStage.setHeight(uiSettings.getLowestRes() /2.0);
        primaryStage.setWidth(uiSettings.getLowestRes() /1.5);
        primaryStage.setTitle(uiSettings.getApplicationName());
        if (Files.exists(uiSettings.getApplicationIconPath())) {
            try {
                primaryStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
            }
            catch (MalformedURLException ignored) { }
        }
        presenter.windowsHandler();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
