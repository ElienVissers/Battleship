package Battleship.view;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;

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
    };

    public int getResX () {return this.resX;}

    public int getResY () {return this.resY;}

    public int getInsetsMargin () {return this.insetsMargin;}

    public int getLowestRes () {return (Math.min(resX, resY));}

    public boolean styleSheetAvailable (){return Files.exists(styleSheetPath);}

    public Path getStyleSheetPath () {return this.styleSheetPath;}

    public void setStyleSheetPath (Path styleSheetPath) {this.styleSheetPath = styleSheetPath;}

    public String getHomeDir () {return this.homeDir;}

    public Path getApplicationIconPath () {return this.applicationIconPath;}

    public Path getStartScreenImagePath () {return this.startScreenImagePath;}

    public Path getAboutImagePath () {return this.AboutImagePath;}

    public Path getInfoTextPath () {return this.infoTextPath;}

    public String getApplicationName () {return this.ApplicationName;}

    public static void getCloseAlert(Event event, Scene scene) {
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
            scene.getWindow().hide();
        }
    }
}