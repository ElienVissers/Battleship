package Battleship.view;

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;


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

    public UISettings() {
        this.resX= (int) Screen.getPrimary().getVisualBounds().getWidth();
        this.resY = (int) Screen.getPrimary().getVisualBounds().getHeight();
        this.insetsMargin = this.getLowestRes()/100;
        String homeDir = System.getProperties().getProperty("user.dir");
        this.ApplicationName = "Battleship";
    }

    public int getResX () {return this.resX;}
    public int getInsetsMargin () {return this.insetsMargin;}
    public int getLowestRes () {return (Math.min(resX, resY));}
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
            dialogPane.getStylesheets().add("/stylesheets/battleship_standard.css");
            dialogPane.getStyleClass().add("alert-window");
        } catch (Exception ignored) {}
        try {
            Stage stage = (Stage) stopWindow.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("images/ApplicationLogo.png"));
        }
        catch (Exception ignored) { }
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