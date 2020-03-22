package Battleship.view;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Contains "User Interface" information that will be used by the application.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020
 */

public class UISettings {

    private int resX;
    private int resY;
    private int insetsMargin;
    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private String ApplicationName;
    private String homeDir;
//    private Path styleSheetPath = Paths.get("resources/stylesheets/battleship_standard.css");
//    private Path AboutImagePath = Paths.get("resources/images/ApplicationImage.gif");
//    private Path applicationIconPath = Paths.get("resources/images/ApplicationLogo.png");
//    private Path startScreenImagePath = Paths.get("resources/images/ApplicationImage.gif");
//    private Path infoTextPath = Paths.get("resources/other/info.txt");
//    private Path highscoresPath = Paths.get("resources/other/highscores.txt");

    public UISettings() {
        this.resX= (int) Screen.getPrimary().getVisualBounds().getWidth();
        this.resY = (int) Screen.getPrimary().getVisualBounds().getHeight();
        this.insetsMargin = this.getLowestRes()/100;
        this.homeDir = System.getProperties().getProperty("user.dir");
        this.ApplicationName = "Battleship";
    }

    public int getResX () {return this.resX;}

    public int getInsetsMargin () {return this.insetsMargin;}

    public int getLowestRes () {return (Math.min(resX, resY));}

//    public boolean styleSheetAvailable (){return Files.exists(styleSheetPath);}
//
//    public Path getStyleSheetPath() {return this.styleSheetPath;}
//
//    public Path getApplicationIconPath () {return this.applicationIconPath;}
//
//    public Path getStartScreenImagePath () {return this.startScreenImagePath;}
//
//    public Path getAboutImagePath () {return this.AboutImagePath;}
//
//    public Path getInfoTextPath () {return this.infoTextPath;}
//
//    public Path getHighscoresPath () {return this.highscoresPath;}

    public String getApplicationName () {return this.ApplicationName;}

    public static char getFileSeparator() {
        return FILE_SEPARATOR;
    }

    /**
     * Opens an Alert DialogPane when the user tries to close the application.
     *
     *  @param event The event that would normally be triggered (the closing of the application)
     *  @param scene The scene that would be closed
     */
    public static void getCloseAlert(Event event, Scene scene) {
        getCloseAlert(event, scene,"You're closing the application.","Are you sure? Unsaved data may be lost.");
    }

    /**
     * Opens an Alert DialogPane when the user tries to close a window.
     *
     * @param event The event that would normally be triggered (the closing of the window)
     * @param scene The scene that would be closed
     * @param headerText The header-text of the Alert
     * @param contentText The content-text of the Alert
     */
    public static void getCloseAlert(Event event, Scene scene, String headerText, String contentText){
        final Alert stopWindow = new Alert(Alert.AlertType.CONFIRMATION);
        try {
            DialogPane dialogPane = stopWindow.getDialogPane();
            dialogPane.setGraphic(new Label());
            dialogPane.getStylesheets().add(Paths.get("resources/stylesheets/battleship_standard.css").toUri().toURL().toString());
            dialogPane.getStyleClass().add("alert-window");
        } catch (Exception ignored) {}
        if (Files.exists(Paths.get("resources/images/ApplicationLogo.png"))) {
            try {
                Stage stage = (Stage) stopWindow.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(Paths.get("resources/images/ApplicationLogo.png").toUri().toURL().toString()));
            }
            catch (Exception ignored) { }
        }
        stopWindow.setHeaderText(headerText);
        stopWindow.setContentText(contentText);
        stopWindow.setTitle("WARNING!");
        stopWindow.getButtonTypes().clear();
        ButtonType noButton = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType yesButton = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
        stopWindow.getButtonTypes().addAll(yesButton, noButton);
        stopWindow.showAndWait();
        if (stopWindow.getResult() == ButtonType.OK || stopWindow.getResult().equals(noButton)) {
            event.consume();
        } else {
            scene.getWindow().hide();
        }
    }
}