package Battleship.view.highscoresscreen;

import Battleship.model.BattleshipModel;
import Battleship.view.UISettings;

import java.io.*;

/**
 * The Presenter class of the HighscoresScreen.
 *
 * @author Jan Dubois
 * @version 1.0 15.02.2020 14:03
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
        InputStream inputStream = getClass().getResourceAsStream("/other/highscores.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine())!= null){
                sb.append(line);
                sb.append("\n");
                highscoresFile += line + "\n";
            }
        } catch (Exception ignored) {}

        return (highscoresFile.compareTo("")==0)?"No highscores available":highscoresFile;
    }

}
