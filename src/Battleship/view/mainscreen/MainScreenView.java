package Battleship.view.mainscreen;

import Battleship.view.UISettings;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.Random;

/**
 * The View class of the MainScreen.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020
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
    private ImageView animatedShip1;
    private ImageView animatedShip2;

    private String[] enemies = {"Yoda", "Anakin Skywalker", "Darth Vader", "Obi-Wan Kenobi", "Luke Skywalker", "Han Solo", "R2-D2", "C-3PO"};
    private Boolean isComputer = false;

    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
        animate();
    }

    private void initialiseNodes() {
        //MENU
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");
        this.exitMI = new MenuItem("Exit");
        this.computerPlayerCheck = new CheckMenuItem("Virtual opponent");
        this.optionsMI = new MenuItem("Universe settings");
        this.highscoresMI = new MenuItem("Highscores");
        //CONTENT
        this.player1Label = new Label("Player 1");
        this.player2Label = new Label("Player 2");
        this.player1Label.getStyleClass().addAll("title", "red-text");
        this.player2Label.getStyleClass().addAll("title", "blue-text");
        this.player1Text = new TextField();
        this.player2Text = new TextField();
        this.player1Text.setId("textfield-player1");
        this.player2Text.setId("textfield-player2");
        this.startButton = new Button("START");
        //ANIMATION
        this.animatedShip1 = new ImageView(new Image("/images/ship_red_3.png", 150, 50, true, true));
        this.animatedShip2 = new ImageView(new Image("/images/ship_blue_4_left.png", 200, 50, true, true));
    }

    private void layoutNodes() {
        addMenu();
        addContent();
        addAnimation();
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
        content.setPadding(new Insets(200, 0, 0, 0));
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

    private void addAnimation() {
        animatedShip1.setTranslateX(-350);
        animatedShip2.setTranslateX(uiSettings.getResX() + 1350);
        VBox animatedShipBox = new VBox();
        animatedShipBox.getChildren().addAll(animatedShip1, animatedShip2);
        animatedShipBox.setSpacing(100);
        animatedShipBox.setPadding(new Insets(0, 0, 50, 0));
        setBottom(animatedShipBox);
    }

    private void animate() {
        TranslateTransition fly1 = new TranslateTransition();
        fly1.setNode(animatedShip1);
        fly1.setDuration(Duration.seconds(25));
        fly1.setByX(uiSettings.getResX() + 500);
        fly1.setCycleCount(1);
        fly1.setInterpolator(Interpolator.LINEAR);
        TranslateTransition fly2 = new TranslateTransition();
        fly2.setNode(animatedShip2);
        fly2.setDuration(Duration.seconds(25));
        fly2.setByX(- uiSettings.getResX() - 1700);
        fly2.setCycleCount(1);
        fly2.setInterpolator(Interpolator.LINEAR);
        ParallelTransition fly = new ParallelTransition();
        fly.getChildren().addAll(fly1, fly2);
        fly.setCycleCount(Timeline.INDEFINITE);
        fly.play();
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
    Boolean isComputer() {return isComputer;}
    TextField getPlayer1Text() {return player1Text;}
    TextField getPlayer2Text() {return player2Text;}
    Button getStartButton() {return startButton;}

    void setPlayerMode() {
        if(player2Text.isEditable()) {
            Random rand = new Random();
            int index = rand.nextInt(enemies.length);
            player2Text.setText(enemies[index]);
            player2Text.setEditable(false);
            isComputer = true;
        } else {
            player2Text.setEditable(true);
            player2Text.setText("");
            isComputer = false;
        }
        addContent();
    }

}
