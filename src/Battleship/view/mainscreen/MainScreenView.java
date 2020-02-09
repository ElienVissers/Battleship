package Battleship.view.mainscreen;

import Battleship.view.UISettings;
import javafx.scene.layout.*;
import javafx.scene.control.*;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 09.02.2020 14:08
 */

public class MainScreenView extends BorderPane  {

    private MenuItem exitMI;
    private MenuItem saveMI;
    private MenuItem loadMI;
    private MenuItem settingsMI;
    private MenuItem aboutMI;
    private MenuItem infoMI;
    private UISettings uiSettings;

    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.saveMI = new MenuItem("Save");
        this.loadMI = new MenuItem("Load");
        this.settingsMI = new MenuItem("Settings");
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File",null,loadMI, saveMI, new SeparatorMenuItem(), settingsMI, new SeparatorMenuItem(),exitMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getSaveItem() {return saveMI;}

    MenuItem getLoadItem() {return loadMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}

}
