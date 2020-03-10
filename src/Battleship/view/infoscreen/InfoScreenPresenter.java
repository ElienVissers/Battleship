package Battleship.view.infoscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;

import java.io.BufferedReader;
import java.io.FileReader;


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
        try (BufferedReader reader = new BufferedReader(new FileReader(uiSettings.getInfoTextPath().toString()))){
            String line = "";
            while ((line = reader.readLine())!= null){
                infoTextInFile += line + "\n";
            }
        } catch (Exception ignored) {}
        return (infoTextInFile.compareTo("")==0)?"No info available":infoTextInFile;
    }
}
