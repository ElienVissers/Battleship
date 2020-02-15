package Battleship.view.mainscreen;

import Battleship.view.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:08
 */

public class MainScreenView extends BorderPane  {

    private MenuItem aboutMI;
    private MenuItem infoMI;
    private MenuItem exitMI;
    private MenuItem optionsMI;
    private MenuItem highscoresMI;
    private CheckMenuItem computerPlayerCheck;
    private Label player1Label;
    private Label player2Label;
    private TextField player1Text;
    private TextField player2Text;
    private UISettings uiSettings;

    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //MENU
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");
        this.exitMI = new MenuItem("Exit");
        this.computerPlayerCheck = new CheckMenuItem("Virtual opponent");
        this.optionsMI = new MenuItem("Options");
        this.highscoresMI = new MenuItem("Highscores");
        //CONTENT
        this.player1Label = new Label("Player 1");
        this.player2Label = new Label("Player 2");
        this.player1Text = new TextField();
        this.player2Text = new TextField();

    }

    private void layoutNodes() {
        //MENU
        Menu helpM = new Menu("Help",null, aboutMI, infoMI, new SeparatorMenuItem(), exitMI);
        Menu optionsM = new Menu("Options",null, computerPlayerCheck, optionsMI, new SeparatorMenuItem(), highscoresMI);
        MenuBar menuBar = new MenuBar(optionsM, helpM);
        setTop(menuBar);
        //CONTENT
        VBox player1 = new VBox();
        VBox player2 = new VBox();
        layoutVBox(player1, player1Label, player1Text);
        layoutVBox(player2, player2Label, player2Text);
        HBox playerBox = new HBox();
        layoutHBox(playerBox, player1, player2);
        this.setCenter(playerBox);
    }

    private void layoutVBox(VBox box, Label label, TextField text) {
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        box.getChildren().addAll(label, text);
    }

    private void layoutHBox(HBox box, VBox childBox1, VBox childBox2) {
        box.getChildren().addAll(childBox1, childBox2);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(200);
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}

    MenuItem getOptionsItem() {return optionsMI;}

    MenuItem getHighscoresItem() {return highscoresMI;}

    MenuItem getComputerPlayerCheck() {return computerPlayerCheck;}

}
