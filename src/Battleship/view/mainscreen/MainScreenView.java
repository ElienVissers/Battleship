package Battleship.view.mainscreen;

import Battleship.view.UISettings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.util.Random;

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
    private Button startButton;
    private UISettings uiSettings;

    private boolean computerPlayer = false;
    private String[] enemies = {"Yoda", "Anakin Skywalker", "Darth Vader", "Obi-Wan Kenobi", "Luke Skywalker", "Han Solo", "R2-D2", "C-3PO"};

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
        this.player1Text.setPrefWidth(150);
        this.player2Text.setPrefWidth(150);
        this.startButton = new Button("START");
        if (computerPlayerCheck.isSelected()) {
            computerPlayer = true;
        }
    }

    private void layoutNodes() {
        addMenu();
        addContent();
    }

    private void addMenu() {
        Menu helpM = new Menu("Help",null, aboutMI, infoMI, new SeparatorMenuItem(), exitMI);
        Menu optionsM = new Menu("Options",null, computerPlayerCheck, optionsMI, new SeparatorMenuItem(), highscoresMI);
        MenuBar menuBar = new MenuBar(optionsM, helpM);
        setTop(menuBar);
    }

    private void addContent() {
        //--playerbox
        VBox player1 = new VBox();
        VBox player2 = new VBox();
        layoutVBox(player1, player1Label, player1Text, 20);
        layoutVBox(player2, player2Label, player2Text, 20);
        HBox playerBox = new HBox();
        layoutHBox(playerBox, player1, player2);
        //--button
        layoutButton(startButton);
        //--content
        VBox content = new VBox();
        layoutVBox(content, playerBox, startButton, 100);
        this.setCenter(content);
    }

    private void layoutVBox(VBox box, Node upper, Node lower, int spacing) {
        box.setAlignment(Pos.CENTER);
        box.setSpacing(spacing);
        box.getChildren().addAll(upper, lower);
    }

    private void layoutHBox(HBox box, VBox childBox1, VBox childBox2) {
        box.getChildren().addAll(childBox1, childBox2);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(200);
    }

    private void layoutButton(Button button) {
        button.setAlignment(Pos.CENTER);
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}

    MenuItem getOptionsItem() {return optionsMI;}

    MenuItem getHighscoresItem() {return highscoresMI;}

    MenuItem getComputerPlayerCheck() {return computerPlayerCheck;}

    void setPlayerMode() {
        if(player2Text.isEditable()) {
            Random rand = new Random();
            int index = rand.nextInt(enemies.length - 1);
            player2Text.setText(enemies[index]);
            player2Text.setEditable(false);
        } else {
            player2Text.setEditable(true);
            player2Text.setText("");
        }
        addContent();
    }

}
