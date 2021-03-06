package Battleship.view.infoscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The Presenter class of the InfoScreen.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 05.03.2020
 */
public class InfoScreenPresenter {

    private BattleshipModel model;
    private InfoScreenView view;
    private UISettings uiSettings;

    public InfoScreenPresenter(BattleshipModel model, InfoScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        view.getInfoText().setText(ReadInfoFromFile());
    }

    private String ReadInfoFromFile() {
        String infoTextInFile ="";
        InputStream inputStream = getClass().getResourceAsStream("/other/info.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = "";
            while ((line = reader.readLine())!= null){
                infoTextInFile += line + "\n";
            }
        } catch (Exception ignored) {}
        return (infoTextInFile.compareTo("")==0)?"No info available":infoTextInFile;
    }
}
