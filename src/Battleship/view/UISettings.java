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
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:07
 */

public class UISettings {

    private int resX;
    private int resY;
    private int insetsMargin;
    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private String ApplicationName;
    private String homeDir;
    private String defaultCss = "battleship_standard.css";
    private Path styleSheetPath = Paths.get("resources"+FILE_SEPARATOR+"stylesheets"+FILE_SEPARATOR+defaultCss);
    private Path AboutImagePath = Paths.get("resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"ApplicationImage.gif");
    private Path applicationIconPath = Paths.get("resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"ApplicationLogo.png");
    private Path startScreenImagePath = Paths.get("resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"ApplicationImage.gif");
    private Path infoTextPath = Paths.get("resources"+FILE_SEPARATOR+"other"+FILE_SEPARATOR+"info.txt");

    public UISettings() {
        this.resX= (int) Screen.getPrimary().getVisualBounds().getWidth();
        this.resY = (int) Screen.getPrimary().getVisualBounds().getHeight();
        this.insetsMargin = this.getLowestRes()/100;
        this.homeDir = System.getProperties().getProperty("user.dir");
        this.ApplicationName = "Battleship";
    }

    public int getResX () {return this.resX;}

    public int getResY () {return this.resY;}

    public int getInsetsMargin () {return this.insetsMargin;}

    public int getLowestRes () {return (Math.min(resX, resY));}

    public boolean styleSheetAvailable (){return Files.exists(styleSheetPath);}

    public Path getStyleSheetPath() {return this.styleSheetPath;}

    public void setStyleSheetPath (Path styleSheetPath) {this.styleSheetPath = styleSheetPath;}

    public String getHomeDir () {return this.homeDir;}

    public Path getApplicationIconPath () {return this.applicationIconPath;}

    public Path getStartScreenImagePath () {return this.startScreenImagePath;}

    public Path getAboutImagePath () {return this.AboutImagePath;}

    public Path getInfoTextPath () {return this.infoTextPath;}

    public String getApplicationName () {return this.ApplicationName;}

    public static char getFileSeparator() {
        return FILE_SEPARATOR;
    }

    public static void getCloseAlert(Event event, Scene scene) {
        final Alert stopWindow = new Alert(Alert.AlertType.CONFIRMATION);
        try {
            DialogPane dialogPane = stopWindow.getDialogPane();
            dialogPane.setGraphic(new Label());
            dialogPane.getStylesheets().add(Paths.get("resources"+FILE_SEPARATOR+"stylesheets"+FILE_SEPARATOR+"battleship_standard.css").toUri().toURL().toString());
            dialogPane.getStyleClass().add("alert-window");
        } catch (Exception ignored) {}
        if (Files.exists(Paths.get("resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"ApplicationLogo.png"))) {
            try {
                Stage stage = (Stage) stopWindow.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(Paths.get("resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"ApplicationLogo.png").toUri().toURL().toString()));
            }
            catch (Exception ignored) { }
        }
        stopWindow.setHeaderText("You're closing the application.");
        stopWindow.setContentText("Are you sure? Unsaved data may be lost.");
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