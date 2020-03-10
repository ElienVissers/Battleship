package Battleship.view.victoryscreen;

import Battleship.model.BattleshipException;
import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import Battleship.view.mainscreen.MainScreenPresenter;
import Battleship.view.mainscreen.MainScreenView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.nio.file.Files;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 15.02.2020 12:35
 */
public class VictoryScreenPresenter {

    private BattleshipModel model;
    private VictoryScreenView view;
    private UISettings uiSettings;

    public VictoryScreenPresenter(BattleshipModel model, VictoryScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;

        updateView();
        EventHandlers();

        try {
            this.model.saveGame();
        } catch (BattleshipException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            try {
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(new Label());
                dialogPane.getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                dialogPane.getStyleClass().add("alert-window");
            } catch (Exception ignored) {}
            alert.setTitle("unable to save game");
            alert.setContentText(e.getMessage());
            if (Files.exists(uiSettings.getApplicationIconPath())) {
                try {
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                }
                catch (Exception ignored) { }
            }
            alert.show();
        }
    }

    private void updateView() {
        this.view.getVictoryLabel().setText("Congratulations, " + model.getPassivePlayer().getName() + "! You are one step closer to ruling the galaxy!");
        this.view.getVictoryLabel().getStyleClass().addAll("title", model.getPassivePlayer().getColor() + "-text");
        this.view.getAnimatedShip1().setImage(new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_" + model.getPassivePlayer().getColor() + "_3.png", 150, 50, true, true));
        this.view.getAnimatedShip2().setImage(new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_" + model.getPassivePlayer().getColor() + "_4.png", 200, 50, true, true));
        this.view.getAnimatedShip3().setImage(new Image(UISettings.getFileSeparator() + "images" + UISettings.getFileSeparator() + "ship_" + model.getPassivePlayer().getColor() + "_5.png", 250, 50, true, true));
    }

    private void EventHandlers() {
        view.getButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainScreenView mainScreenView = new MainScreenView(uiSettings);
                MainScreenPresenter mainScreenpresenter = new MainScreenPresenter(model, mainScreenView, uiSettings);
                view.getScene().setRoot(mainScreenView);
                mainScreenpresenter.windowsHandler();
            }
        });
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { UISettings.getCloseAlert(event, view.getScene()); }
        });
    }
}
