package Battleship.view.mainscreen;

import Battleship.view.UISettings;
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
    private UISettings uiSettings;

    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");
        this.exitMI = new MenuItem("Exit");
        this.computerPlayerCheck = new CheckMenuItem("Virtual opponent");
        this.optionsMI = new MenuItem("Options");
        this.highscoresMI = new MenuItem("Highscores");
    }

    private void layoutNodes() {
        Menu helpM = new Menu("Help",null, aboutMI, infoMI, new SeparatorMenuItem(), exitMI);
        Menu optionsM = new Menu("Options",null, computerPlayerCheck, optionsMI, new SeparatorMenuItem(), highscoresMI);
        MenuBar menuBar = new MenuBar(optionsM, helpM);
        setTop(menuBar);
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}

    MenuItem getOptionsItem() {return optionsMI;}

    MenuItem getHighscoresItem() {return highscoresMI;}

    MenuItem getComputerPlayerCheck() {return computerPlayerCheck;}

}
