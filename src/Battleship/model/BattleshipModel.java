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

    public static void main(String[] args) {
        System.out.println("Run Zeeslag App.");
        //TODO Add a function to get player names and check if computer needs to play
        Game currentGame = new Game(false, args); //possible game extension: play against a computer player

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
