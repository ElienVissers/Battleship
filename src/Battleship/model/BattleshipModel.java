package Battleship.model;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 13:58
 *
 * controls the course of ZEESLAG;
 */

public class BattleshipModel {

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

    private final Random rand = new Random(); //possible game extension; random "verdeling" zodat de kans op kleinere getallen groter is
    private final int GRIDSIZE = 10; //possible game extension; when custom: check GUI what is the maximum that fits on the screen... OR different stylesheets
    private final int NUMBER_OF_SHIPS = 10; //possible game extension; dependant on GRIDSIZE > 5 - 15?

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

    public void startGame(Boolean isComputer, String name1, String name2) {
        availableShips = new int[NUMBER_OF_SHIPS];
        ships = new ArrayList<>();
        date = LocalDate.now();
        gameStarted = 0;
        turnCounter = 0;
        createGameShips();
        createGamePlayers(isComputer, name1, name2);
        createGameBoards();
    }

    private void createGameShips() {
        //minimum one ship of each
        availableShips[0] = 2;
        availableShips[1] = 3;
        availableShips[2] = 4;
        availableShips[3] = 5;
        //random other ships
        for (int i = 4; i < NUMBER_OF_SHIPS; i++) {
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

    private void createGameBoards() {
        gameboards = new HashMap<>();
        for (Player player:players) {
            gameboards.put(player, new GameBoard());
        }
    }

    public void prepareBoard() {
        gameboards.get(activePlayer).addShips(activePlayer.getShipCoordinates());
        gameStarted += 0.5;
        togglePlayer();
    }

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

    public boolean isGameOver() {
        return gameboards.get(activePlayer).getSinkCounter() == NUMBER_OF_SHIPS;
    }

    private void togglePlayer() {
        if (activePlayer.equals(players.get(0))) {
            activePlayer = players.get(1);
            passivePlayer = players.get(0);
        } else if (activePlayer.equals(players.get(1))) {
            activePlayer = players.get(0);
            passivePlayer = players.get(1);
        }
    }

    /*TODO : the game ends*/
    public void endGame() {
        turnCounter = Math.round(turnCounter);
        System.out.println("Write away name of the winning player (" + passivePlayer.getName() + "), the date (" + date.toString() + ") and the amount of turns (" + turnCounter + ")");
        System.out.println("Close the game.");
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getPassivePlayer() {
        return passivePlayer;
    }

    public int getActiveSinkCounter() {
        return gameboards.get(activePlayer).getSinkCounter();
    }

    public double getGameStarted() {
        return gameStarted;
    }

    public int getGridSize() {
        return GRIDSIZE;
    }

    public int getNUMBER_OF_SHIPS() {
        return NUMBER_OF_SHIPS;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
