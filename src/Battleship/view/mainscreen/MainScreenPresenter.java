package Battleship.view.mainscreen;

import Battleship.model.*;
import Battleship.view.aboutscreen.*;
import Battleship.view.highscoresscreen.HighscoresScreenView;
import Battleship.view.highscoresscreen.HighscoresScreenPresenter;
import Battleship.view.infoscreen.*;
import Battleship.view.mainoptionsscreen.*;
import Battleship.view.UISettings;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;
import java.nio.file.Files;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:08
 */

public class MainScreenPresenter {

    private BattleshipModel model;
    private MainScreenView view;
    private UISettings uiSettings;

    public MainScreenPresenter(BattleshipModel model, MainScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        updateView();
        EventHandlers();
    }

    private void updateView() {
    }

    private void EventHandlers() {

        //MENU
        view.getOptionsItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainOptionsScreenView mainOptionsScreenView = new MainOptionsScreenView(uiSettings);
                MainOptionsScreenPresenter mainOptionsScreenPresenter = new MainOptionsScreenPresenter(model, mainOptionsScreenView, uiSettings);
                Stage mainOptionsStage = new Stage();
                openMenuWindow(mainOptionsStage, mainOptionsScreenView, "Options"); //open OPTIONS MENU window
            }
        });
        view.getHighscoresItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO load highscores from file (LATER, geen prioriteit)
                HighscoresScreenView highscoresScreenView = new HighscoresScreenView(uiSettings);
                HighscoresScreenPresenter highscoresScreenPresenter = new HighscoresScreenPresenter(model, highscoresScreenView, uiSettings);
                Stage highscoresStage = new Stage();
                openMenuWindow(highscoresStage, highscoresScreenView, "Highscores"); //open HIGHSCORES MENU window
            }
        });
        view.getExitItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleCloseEvent(event);
            }
        });
        view.getAboutItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AboutScreenView aboutScreenView = new AboutScreenView(uiSettings);
                AboutScreenPresenter aboutScreenPresenter = new AboutScreenPresenter(model, aboutScreenView, uiSettings);
                Stage aboutScreenStage = new Stage();
                openMenuWindow(aboutScreenStage, aboutScreenView, "About"); //open ABOUT MENU window
            }});
        view.getInfoItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InfoScreenView infoScreenView = new InfoScreenView(uiSettings);
                InfoScreenPresenter infoScreenPresenter = new InfoScreenPresenter(model, infoScreenView, uiSettings);
                Stage infoScreenStage = new Stage();
                openMenuWindow(infoScreenStage, infoScreenView, "Info"); //open INFO MENU window
            }});
        view.getComputerPlayerCheck().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.setPlayerMode();
            }
        });

        //CONTENT
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.start(view.isComputer(), view.getPlayer1Text().getText(), view.getPlayer2Text().getText());
                /*
                    TODO
                     1. "van scherm A naar scherm B in bestaande Stage", zie ppt.
                     2. wegwerken van de "infinite wait"-situatie die er nu is...
                            = waarschijnlijk aanpassing maken in de BattleshipModel-klasse;
                            dit is nu geen "main-methode" meer, krijgt geen input meer van command line,
                            maar hij wacht nog steeds op input ("which cell do you want to target?"),
                            zonder dat er nu input gegeven kan worden...
                            DUS: BattleshipModel herschrijven? Kijken naar de voorbeeldprojecten? Hoe is het "model" daar opgebouwd?
                 */
            }});
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { handleCloseEvent(event); }});
    }

    private void openMenuWindow(Stage stage, Parent newView, String name) {
        stage.setTitle(name);
        stage.initOwner(view.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(newView);
        stage.setScene(scene);
        stage.setTitle(uiSettings.getApplicationName() + " - " + name);
        stage.setX(view.getScene().getWindow().getX() + uiSettings.getResY() / 20.0);
        stage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 10.0);
        if (Files.exists(uiSettings.getApplicationIconPath())) {
            try {
                stage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
            } catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        }
        if (uiSettings.styleSheetAvailable()) {
            stage.getScene().getStylesheets().removeAll();
            try {
                stage.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
            } catch (MalformedURLException ex) {
                // do nothing, if toURL-conversion fails, program can continue
            }
        }
        stage.showAndWait();
    }

    private void handleCloseEvent(Event event){
        final Alert stopWindow = new Alert(Alert.AlertType.CONFIRMATION);
        stopWindow.setHeaderText("You're closing the application.");
        stopWindow.setContentText("Are you sure? Unsaved data may be lost.");
        stopWindow.setTitle("WARNING!");
        stopWindow.getButtonTypes().clear();
        ButtonType noButton = new ButtonType("No");
        ButtonType yesButton = new ButtonType("Yes");
        stopWindow.getButtonTypes().addAll(yesButton, noButton);
        stopWindow.showAndWait();
        if (stopWindow.getResult() == null || stopWindow.getResult().equals(noButton)) {
            event.consume();
        } else {
            view.getScene().getWindow().hide();
        }
    }

}
