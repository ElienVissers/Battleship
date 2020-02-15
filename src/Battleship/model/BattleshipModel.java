package Battleship.model;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 14:51
 *
 * BATTLESHIP application; creates and executes a new "game"
 */

public class BattleshipModel {

    public BattleshipModel() { }

    private static boolean gameRuns = true;

    public static void start(Boolean playerMode, String name1, String name2) {
        System.out.println("Run Zeeslag App.");
        Game currentGame = new Game(playerMode, name1, name2);

        currentGame.prepareGrids();
        while (gameRuns) {
            currentGame.activePlayerPlays();
            if (currentGame.isGameOver()) {
                gameRuns = false;
                break;
            }
            currentGame.togglePlayer();
        }
        currentGame.endGame();
    }
}
