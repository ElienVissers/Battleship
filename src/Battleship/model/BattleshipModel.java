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

    private final Random rand = new Random();
    private final int GRIDSIZE = 10; //possible game extension; when custom: MAX 25!
    private final int NUMBER_OF_SHIPS = 10; //possible game extension

    private List<Player> players;
    private Player activePlayer;
    private Player passivePlayer;
    private int activeIndex;
    int[] availableShips = new int[NUMBER_OF_SHIPS];
    private List<Ship> ships;
    private Map<Player, GameBoard> grids;

    private LocalDate date;
    private String winner;

    private int turnCounter;

    public BattleshipModel() {
        date = LocalDate.now();
        turnCounter = 0;
    }

    public void startGame(Boolean isComputer, String name1, String name2) {
        createGameShips();
        createGamePlayers(isComputer, name1, name2);
    }

    /*create ships*/
    public void createGameShips() {
        for (int i = 0; i < NUMBER_OF_SHIPS; i++) {
            availableShips[i] = rand.nextInt(4) + 2;
        }
        ships = new ArrayList<>();
        for (int size: availableShips) {
            Ship ship;
            switch (size) {
                case 2: ship = Ship.STARFIGHTER; break;
                case 3: ship = Ship.STARDISCOVERER; break;
                case 4: ship = Ship.STARDESTROYER; break;
                case 5: ship = Ship.STARCRUISER; break;
                default: ship = null;
            }
            ships.add(ship);
        }
    }

    /*create players*/
    public void createGamePlayers(boolean isComputer, String name1, String name2) {
        players = new ArrayList<>();
        Player player1 = new HumanPlayer(name1, ships);
        Player player2;
        if (isComputer) {
            player2 = new ComputerPlayer(name2, ships);
        } else {
            player2 = new HumanPlayer(name2, ships);
        }
        players.add(player1);
        players.add(player2);
        activePlayer = players.get(0);
        passivePlayer = players.get(1);
        activeIndex = 0;
    }

    /*create grids*/
    public void createGameGrids() {
        grids = new HashMap<>();
        for (Player player:players) {
            grids.put(player, new GameBoard(GRIDSIZE));
        }
    }

    /*allows every player to position ships on the map*/
    public void prepareGrids() {
        for (Player ignored : players) {
            grids.get(activePlayer).addShips(activePlayer.positionShips());
            togglePlayer();
        }
    }

    /*the active player plays his/her turn*/
    public void activePlayerPlays() {
        Square targetSquare = activePlayer.fireRocket();
        while (grids.get(passivePlayer).hasBeenTargeted(targetSquare)) {
            //TODO Java FX GUI
            System.out.println("This cell (" + targetSquare.getCoordinates() + ") has already been targeted.");
            targetSquare = activePlayer.fireRocket();
        }
        if (!grids.get(passivePlayer).hasBeenTargeted(targetSquare)) {
            if (grids.get(passivePlayer).hitOrMiss(targetSquare)) {
                //TODO Java FX GUI
                System.out.println("HIT!");
                grids.get(passivePlayer).hasSunken(targetSquare);
            } else {
                //TODO Java FX GUI
                System.out.println("MISS!");
            }
        }
        turnCounter++; //TODO divide by 2 at end of the game
    }

    /*check if the game is over*/
    public boolean isGameOver() {
        if (grids.get(passivePlayer).getSinkCounter() == NUMBER_OF_SHIPS) {
            return true;
        }
        //FIXME remove this test if-block when positionShips() is OK
        if (grids.get(passivePlayer).getSinkCounter() == 1) {
            return true;
        }
        return false;
    }

    /*the players switch turns*/
    public void togglePlayer() {
        if (activePlayer.equals(players.get(0))) {
            activePlayer = players.get(1);
            passivePlayer = players.get(0);
            activeIndex = 1;
        } else if (activePlayer.equals(players.get(1))) {
            activePlayer = players.get(0);
            passivePlayer = players.get(1);
            activeIndex = 0;
        }
        System.out.println("It's " + activePlayer.getName() + "'s turn now.");
    }

    /*the game ends*/
    //TODO
    public void endGame() {
        System.out.println("In-game message congratulates winning player " + activePlayer.getName());
        System.out.println("Write away name of the winning player, the date and the amount of turns");
        System.out.println("Close the game.");
    }

    public String getActivePlayerName() {
        return activePlayer.getName();
    }

    public String getActivePlayerColor() {
        String color = "";
        if (activeIndex == 0) {
            color = "red";
        } else if (activeIndex ==1) {
            color = "blue";
        }
        return color;
    }

    public int[] getAvailableShips() {
        return availableShips;
    }
}
