package Battleship.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020 16:16
 *
 * saves ship positions;
 * remembers where rockets land;
 * counts how many ships have sunk;
 */

public class GameBoard {
    /*a map that holds the coordinates as a key, and the corresponding cell as a value*/
    private Map<String, Square> gridMap;

    /*a map with all ships on the grid*/
    private Map<Ship, List<Square>> shipMap;

    /*an integer that counts how many ships have sunken*/
    private int sinkCounter;

    public GameBoard(int gridsize) {
        sinkCounter = 0;
        gridMap = new HashMap<>();
        shipMap = new HashMap<>();
        char letter = 'A';
        for (int letterLoop = 0; letterLoop < gridsize; letterLoop++) {
            for (int numberLoop = 1; numberLoop <= gridsize; numberLoop++) {
                String part1 = Character.toString(letter);
                String part2 = Integer.toString(numberLoop);
                String coordinate = part1 + part2;
                gridMap.put(coordinate, new Square(coordinate));
            }
            letter++;
        }
    }

    public int getSinkCounter() {
        return sinkCounter;
    }

    /*add ships to the grid: update shipMap and gridMap*/
    public void addShips(Map<Ship, List<Square>> shipMapArg) {
        for (Ship ship : shipMapArg.keySet()) {
            this.shipMap.put(ship, shipMapArg.get(ship));
            for (Square square : shipMapArg.get(ship)) {
                gridMap.get(square.getCoordinates()).setShip();
            }
        }
    }

    /*checks if a cell on the grid has been targeted before*/
    public boolean hasBeenTargeted(Square square) {
        if (gridMap.get(square.getCoordinates()).wasTargeted()) {
            return true;
        } else {
            return false;
        }
    }

    /*marks the cell as targeted and checks if the rocket hit a ship (hit) or not (miss)*/
    public boolean hitOrMiss(Square square) {
        gridMap.get(square.getCoordinates()).setTargeted();
        if (gridMap.get(square.getCoordinates()).isShip()) {
            return true;
        } else {
            return false;
        }
    }

    /*checks if the rocket delivered the final blow to a ship*/
    public void hasSunken(Square square) {
        //identify the targeted ship in shipMap
        Ship targetedShip = null;
        for (Ship ship : shipMap.keySet()) {
            for (Square c : shipMap.get(ship)) {
                if (c.getCoordinates().equals(square.getCoordinates())) {
                    targetedShip = ship;
                }
            }
        }
        //loop through the status of the cells on the ship in gridMap
        if (targetedShip != null) {
            int i = 0;
            int j = 0;
            for (i = 0; i < shipMap.get(targetedShip).size(); i++) {
                if (gridMap.get(shipMap.get(targetedShip).get(i).getCoordinates()).wasTargeted()) {
                    j++;
                }
            }
            if (i == j) {
                sinkCounter++;
                //TODO Java FX GUI
                System.out.println("The enemy's ship '" + targetedShip.getName() + "' has sunken!");
                System.out.println("A total of " + sinkCounter + " ships have been destroyed.");
            }
        }
    }
}
