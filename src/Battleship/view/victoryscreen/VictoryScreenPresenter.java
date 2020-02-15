package Battleship.view.victoryscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;
import Battleship.view.mainscreen.MainScreenView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;

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
    }

    private void updateView() { }

    private void EventHandlers() {
        view.getSaveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Data File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Textfiles", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
                File selectedFile = fileChooser.showSaveDialog(view.getScene().getWindow());
                if ((selectedFile != null) ^ (Files.isWritable(Paths.get(selectedFile.toURI())))) {
                    try {
                        Files.deleteIfExists(Paths.get(selectedFile.toURI()));
                    } catch (IOException e) {
                        //
                    }
                    try (Formatter output = new Formatter(selectedFile)) {
                        // Begin implementeren wegschrijven model-gegevens
                        output.format("%s%n", "Here comes the data!");
                        output.format("%s%n", "First record");
                        output.format("%s%n", "...");
                        output.format("%s%n", "Last record");
                        // Einde implementeren wegschrijven model-gegevens
                    } catch (IOException e) {
                        //
                    }
                } else {
                    Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                    errorWindow.setHeaderText("Problem with the selected output file:");
                    errorWindow.setContentText("File is not writable");
                    errorWindow.showAndWait();
                }
            }
        });
    }

}
