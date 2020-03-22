package Battleship.view.highscoresscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;

import java.io.*;

/**
 * The Presenter class of the HighscoresScreen.
 *
 * @author Elien Vissers-Similon, Jan Dubois
 * @version 1.0 15.03.2020
 */
public class HighscoresScreenPresenter {

    private BattleshipModel model;
    private HighscoresScreenView view;
    private UISettings uiSettings;

    public HighscoresScreenPresenter(BattleshipModel model, HighscoresScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        view.getHighscoresText().setText(ReadInfoFromFile());
    }

    private String ReadInfoFromFile() {
        String highscoresFile ="";
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("highscores.bin")))){
            while(dis.available() > 0) {
                String line = dis.readUTF();
                highscoresFile += line + "\n";
            }
        } catch (Exception ignored) {}

        return (highscoresFile.compareTo("")==0)?"No highscores available":highscoresFile;
    }

}
