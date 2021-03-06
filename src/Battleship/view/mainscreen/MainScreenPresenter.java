package Battleship.view.mainscreen;

import Battleship.model.*;
import Battleship.view.aboutscreen.*;
import Battleship.view.highscoresscreen.HighscoresScreenView;
import Battleship.view.highscoresscreen.HighscoresScreenPresenter;
import Battleship.view.infoscreen.*;
import Battleship.view.toggleplayerscreen.TogglePlayerScreenPresenter;
import Battleship.view.toggleplayerscreen.TogglePlayerScreenView;
import Battleship.view.settingsscreen.*;
import Battleship.view.UISettings;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;
import java.nio.file.Files;

/**
 * The Presenter class of the MainScreen.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020
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

    private void updateView() { }

    private void EventHandlers() {
        addMenuEvents();
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (view.getPlayer1Text().getText().isEmpty() || view.getPlayer2Text().getText().isEmpty()) {
                    final Alert stopWindow = new Alert(Alert.AlertType.ERROR);
                    try {
                        DialogPane dialogPane = stopWindow.getDialogPane();
                        dialogPane.setGraphic(new Label());
                        dialogPane.getStylesheets().add("/stylesheets/battleship_standard.css");
                        dialogPane.getStyleClass().add("alert-window");
                    } catch (Exception ignored) {}
                    try {
                        Stage stage = (Stage) stopWindow.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image("images/ApplicationLogo.png"));
                    }
                    catch (Exception ignored) { }
                    stopWindow.setTitle("Error!");
                    stopWindow.setHeaderText("You can not yet start the game.");
                    stopWindow.setContentText("Please fill in a name for both players.");
                    stopWindow.showAndWait();
                    event.consume();
                } else {
                    model.startGame(view.isComputer(), view.getPlayer1Text().getText(), view.getPlayer2Text().getText());
                    TogglePlayerScreenView togglePlayerScreenView = new TogglePlayerScreenView(uiSettings);
                    TogglePlayerScreenPresenter togglePlayerScreenpresenter = new TogglePlayerScreenPresenter(model, togglePlayerScreenView, uiSettings);
                    view.getScene().setRoot(togglePlayerScreenView);
                    togglePlayerScreenpresenter.windowsHandler();
                }
            }});
    }

    private void addMenuEvents() {
        view.getOptionsItem().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                SettingsScreenView settingsScreenView = new SettingsScreenView(uiSettings);
                SettingsScreenPresenter settingsScreenPresenter = new SettingsScreenPresenter(model, settingsScreenView, uiSettings);
                Stage mainOptionsStage = new Stage();
                openMenuWindow(mainOptionsStage, settingsScreenView, "Universe settings"); //open SETTINGS MENU window
            }
        });
        view.getHighscoresItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HighscoresScreenView highscoresScreenView = new HighscoresScreenView(uiSettings);
                HighscoresScreenPresenter highscoresScreenPresenter = new HighscoresScreenPresenter(model, highscoresScreenView, uiSettings);
                Stage highscoresStage = new Stage();
                openMenuWindow(highscoresStage, highscoresScreenView, "Highscores"); //open HIGHSCORES MENU window
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
            public void handle(WindowEvent event) { UISettings.getCloseAlert(event, view.getScene()); }
        });
    }

    private void openMenuWindow(Stage stage, Parent newView, String name) {
        stage.setTitle(name);
        stage.initOwner(view.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(newView);
        stage.setScene(scene);
        stage.setTitle(uiSettings.getApplicationName() + " - " + name);
        stage.setHeight(uiSettings.getLowestRes() /2.0);
        stage.setWidth(uiSettings.getLowestRes() /1.0);
        stage.getIcons().add(new Image("images/ApplicationLogo.png"));
        stage.getScene().getStylesheets().removeAll();
        stage.getScene().getStylesheets().add("/stylesheets/battleship_standard.css");
        stage.showAndWait();
    }

}