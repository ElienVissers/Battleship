package Battleship.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

/**
 * The class BattleshipModel is the main class of the application as it contains the game data.
 * For each game it creates a HashMap that contains the a Player as the key and a GameBoard as the value.
 * It lets players make a move, keeps track of the active player and checks whether or not the game is over.
 * After a game has been won, it updates logFile.txt and highscores.txt.
 *
 * It contains the inner Enum "Ship" which defines the possible ships in the game.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020
 */

public class BattleshipModel {

    /**
     * The enum Ship contains the possible ships.
     * Ships have a size and a name.
     *
     * @author Elien Vissers-Similon
     * @version 1.0 02.02.2020
     */
    public enum Ship {
        TWO(2), THREE(3), FOUR(4), FIVE(5);

        private int size;

        Ship(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public String getName() {
            switch (getSize()) {
                case 2: return "Starfighter";
                case 3: return "Stardiscoverer";
                case 4: return "Stardestroyer";
                case 5: return "Starcruiser";
                default: return "a ship";
            }
        }
    }

    private final Random rand = new Random();
    private final int MAX_GRID_SIZE = 15;
    private final int MIN_GRID_SIE = 7;
    private final int MAX_FLEET_SIZE = 15;
    private final int MIN_FLEET_SIZE = 4;
    private int gridSize = 10;
    private int numberOfShips = 10;

    private List<Player> players;
    private Player activePlayer;
    private Player passivePlayer;
    private int[] availableShips;
    private List<Ship> ships;
    private Map<Player, GameBoard> gameboards;
    
    private LocalDate date;

    private double turnCounter;
    private double gameStarted;

    public BattleshipModel() {
    }

    /**
     * Starts a new game: creates the ships, players and gameboards based on the predefined gridsize and number of ships.
     *
     * @param isComputer determines whether the new game is player-versus-player or player-versus-computer
     * @param name1 the name of player 1
     * @param name2 the name of player 2
     */
    public void startGame(Boolean isComputer, String name1, String name2) {
        availableShips = new int[numberOfShips];
        ships = new ArrayList<>();
        date = LocalDate.now();
        gameStarted = 0;
        turnCounter = 0;
        createGameShips();
        createGamePlayers(isComputer, name1, name2);
        createGameBoards();
    }

    /**
     * Called during startGame(). Creates a random collection of ships that will be used during the game.
     */
    private void createGameShips() {
        //minimum one ship of each
        availableShips[0] = 2;
        availableShips[1] = 3;
        availableShips[2] = 4;
        availableShips[3] = 5;
        //random other ships
        for (int i = 4; i < numberOfShips; i++) {
            availableShips[i] = rand.nextInt(4) + 2;
        }
        for (int size: availableShips) {
            Ship ship = null;
            switch (size) {
                case 2: ship = Ship.TWO; break;
                case 3: ship = Ship.THREE; break;
                case 4: ship = Ship.FOUR; break;
                case 5: ship = Ship.FIVE; break;
            }
            ships.add(ship);
        }
    }

    /**
     * Called during startGame(). Creates both players and sets player 1 as the active player.
     */
    private void createGamePlayers(boolean isComputer, String name1, String name2) {
        players = new ArrayList<>();
        Player player1 = new Player(name1, "red", false);
        Player player2;
        if (isComputer) {
            player2 = new Player(name2, "blue", true);
        } else {
            player2 = new Player(name2, "blue", false);
        }
        players.add(player1);
        players.add(player2);
        activePlayer = players.get(0);
        passivePlayer = players.get(1);
    }

    /**
     * Called during startGame(). Creates the HashMap and adds each player as a key, with a new gameboard as the value.
     */
    private void createGameBoards() {
        gameboards = new HashMap<>();
        for (Player player:players) {
            gameboards.put(player, new GameBoard());
        }
    }

    /**
     * Gets the ship coordinates from the activeplayer and adds them to his/her gameboard in the HashMap.
     */
    public void prepareBoard() {
        gameboards.get(activePlayer).addShips(activePlayer.getShipCoordinates());
        gameStarted += 0.5;
        togglePlayer();
    }

    /**
     * Lets the active player make a move:
     * 1. Has a ship been hit? The method checks if the target coordinates are present in the the passive players's gameboard.
     * 2. Has a ship been sunken? The method checks if the target was the last the last coordinate of the ship to be hit.
     *
     * After the move the turn counter is increased and the active and passive player switch roles.
     *
     * @param x the target x-coordinate
     * @param y the target y-coordinate
     * @return an array that holds the results of the players move: if a ship was hit, if the targeted ship sunk, and important data of said ship
     */
    public int[] activePlayerPlays(int x, int y) {
        int[] returnValue = new int[]{0, 0, 0, 0, 0, 0};
        if (gameboards.get(passivePlayer).hitOrMiss(x, y)) {
            returnValue[0] = 1;
            if (gameboards.get(passivePlayer).hasSunken(x, y)) {
                returnValue[1] = 1;
                returnValue[2] = gameboards.get(passivePlayer).getSinkSize();
                returnValue[3] = gameboards.get(passivePlayer).getSinkX();
                returnValue[4] = gameboards.get(passivePlayer).getSinkY();
                returnValue[5] = gameboards.get(passivePlayer).getSinkDirection();
            }
        }
        turnCounter += 0.5;
        togglePlayer();
        return returnValue;
    }

    /**
     * Checks whether the game is over or not.
     * @return isGameOver boolean value
     */
    public boolean isGameOver() {
        return gameboards.get(activePlayer).getSinkCounter() == numberOfShips;
    }

    /**
     * Switches the active and passive player roles.
     */
    private void togglePlayer() {
        if (activePlayer.equals(players.get(0))) {
            activePlayer = players.get(1);
            passivePlayer = players.get(0);
        } else if (activePlayer.equals(players.get(1))) {
            activePlayer = players.get(0);
            passivePlayer = players.get(1);
        }
    }

    /**
     * Saves the game by updating logFile.txt and highscores.txt
     * 1. logFile.txt: contains the gridsize, number of ships, date, name of the winning player and amount of turns for each game
     * 2. highscores.txt: contains the number of wins for each winner in logFile.txt
     *
     * @author Elien Vissers-Similon, Jan Dubois
     */
    public void saveGame() {
        turnCounter = Math.round(turnCounter);
        String logPathString = "resources"+ File.separator +"other"+ File.separator + "logFile.txt";
        String highscoresPathString = "resources"+ File.separator +"other"+ File.separator + "highscores.txt";
        Path logFile = Paths.get(logPathString);
        Path highscoresFile = Paths.get(highscoresPathString);
        Map<String, Long> namesMap;

        if (Files.exists(logFile)) {
            List<String> logList = new ArrayList<>();
            List<String> nameList = new ArrayList<>();
            try (Scanner sc = new Scanner(new File(logPathString))) {
                while (sc.hasNext()) {
                    String log = sc.nextLine();
                    logList.add(log);
                    nameList.add(log.split(";")[3]);
                }
            } catch (FileNotFoundException fnfe) {
                throw new BattleshipException(fnfe);
            }
            try (Formatter fm = new Formatter(logPathString)) {
                for (String log : logList) {
                    fm.format("%s%n", log);
                }
                fm.format("%d;%d;%s;%s;%d%n", gridSize, numberOfShips, date.toString(), passivePlayer.getName(), (int) turnCounter);
            } catch (IOException ioe) {
                throw new BattleshipException(ioe);
            }
            nameList.add(passivePlayer.getName());
            nameList.remove(0);
            namesMap = countNames(nameList);

            try (Formatter fmHS = new Formatter(highscoresPathString)) {
                for ( String key : namesMap.keySet() ) {
                    fmHS.format("%d%22s%22s%n",namesMap.get(key) , ":" , key );
                }
            } catch (IOException ioe) {
                throw new BattleshipException(ioe);
            }

        } else {
            Formatter fm = null;
            Formatter fmHS = null;
            try {
                Files.createFile(logFile);
                Files.createFile(highscoresFile);
                fm =  new Formatter(logPathString);
                fm.format("%s;%s;%s;%s;%s%n%d;%d;%s;%s;%d%n",
                        "gridsize", "amount of ships", "date", "winner", "amount of turns",
                        gridSize, numberOfShips, date.toString(), passivePlayer.getName(), (int) turnCounter);
                fm.close();
                fmHS = new Formatter(highscoresPathString);
                fmHS.format("%-22s:%-22s",passivePlayer.getName(), "1");
                fmHS.close();
            } catch (IOException ioe) {
                throw new BattleshipException(ioe);
            } finally {
                if (fm != null){ fm.close();}
                if (fmHS != null){ fmHS.close();}
            }
        }
    }

    private <T> Map<T, Long> countNames(List<T> inputList) {
        Map<T, Long> resultMap = new HashMap<>();
        for (T name : inputList) {
            if (resultMap.containsKey(name)) {
                resultMap.put(name, resultMap.get(name) + 1L);
            } else {
                resultMap.put(name, 1L);
            }
        }
        return resultMap;
    }

    public Player getActivePlayer() { return activePlayer; }
    public Player getPassivePlayer() { return passivePlayer; }
    public int getActiveSinkCounter() { return gameboards.get(activePlayer).getSinkCounter(); }
    public double getGameStarted() { return gameStarted; }
    public int getGridSize() { return gridSize; }
    public int getNumberOfShips() { return numberOfShips; }
    public List<Ship> getShips() { return ships; }
    public int getMAX_GRID_SIZE() { return MAX_GRID_SIZE; }
    public int getMIN_GRID_SIE() { return MIN_GRID_SIE; }
    public int getMAX_FLEET_SIZE() { return MAX_FLEET_SIZE; }
    public int getMIN_FLEET_SIZE() { return MIN_FLEET_SIZE; }

    public void setGridSize(int gridSize) { this.gridSize = gridSize; }
    public void setNumberOfShips(int numberOfShips) { this.numberOfShips = numberOfShips; }
}
