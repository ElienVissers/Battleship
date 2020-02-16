package Battleship.view.mainscreen;

import Battleship.model.*;
import Battleship.view.aboutscreen.*;
import Battleship.view.highscoresscreen.HighscoresScreenView;
import Battleship.view.highscoresscreen.HighscoresScreenPresenter;
import Battleship.view.infoscreen.*;
import Battleship.view.mainoptionsscreen.*;
import Battleship.view.UISettings;
import Battleship.view.preparegamescreen.PrepareGameScreenPresenter;
import Battleship.view.preparegamescreen.PrepareGameScreenView;
import javafx.event.*;
import javafx.scene.*;
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
        addMenuEvents();
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.startGame(view.isComputer(), view.getPlayer1Text().getText(), view.getPlayer2Text().getText());
                PrepareGameScreenView prepareGameScreenView = new PrepareGameScreenView(uiSettings);
                PrepareGameScreenPresenter prepareGameScreenpresenter = new PrepareGameScreenPresenter(model, prepareGameScreenView, uiSettings);
                view.getScene().setRoot(prepareGameScreenView);
                prepareGameScreenpresenter.windowsHandlers();
            }});
    }

    private void addMenuEvents() {
        view.getOptionsItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainOptionsScreenView mainOptionsScreenView = new MainOptionsScreenView(uiSettings);
                MainOptionsScreenPresenter mainOptionsScreenPresenter = new MainOptionsScreenPresenter(model, mainOptionsScreenView, uiSettings);
                Stage mainOptionsStage = new Stage();
                openMenuWindow(mainOptionsStage, mainOptionsScreenView, "Options"); //open OPTIONS MENU window
                mainOptionsScreenPresenter.windowsHandler();
            }
        });
        view.getHighscoresItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO load highscores from file (LATER, no priority)
                HighscoresScreenView highscoresScreenView = new HighscoresScreenView(uiSettings);
                HighscoresScreenPresenter highscoresScreenPresenter = new HighscoresScreenPresenter(model, highscoresScreenView, uiSettings);
                Stage highscoresStage = new Stage();
                openMenuWindow(highscoresStage, highscoresScreenView, "Highscores"); //open HIGHSCORES MENU window
                highscoresScreenPresenter.windowsHandler();
            }
        });
        view.getExitItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UISettings.getCloseAlert(event, view.getScene());
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
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { UISettings.getCloseAlert(event, view.getScene()); }});
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

}