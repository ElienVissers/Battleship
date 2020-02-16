package Battleship.model;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 14:51
 *
 * BATTLESHIP application; creates and executes a new "game"
 */

public class BattleshipModel_OLD_MAIN {

    public BattleshipModel_OLD_MAIN() { }

    private static boolean gameRuns = true;

    public static void start(Boolean playerMode, String name1, String name2) {
        System.out.println("Run Zeeslag App.");
        BattleshipModel currentBattleshipModel = new BattleshipModel();

        currentBattleshipModel.prepareGrids();
        while (gameRuns) {
            currentBattleshipModel.activePlayerPlays();
            if (currentBattleshipModel.isGameOver()) {
                gameRuns = false;
                break;
            }
            currentBattleshipModel.togglePlayer();
        }
        currentBattleshipModel.endGame();
    }
}
