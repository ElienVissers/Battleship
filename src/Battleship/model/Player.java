package Battleship.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the player's ability to position his/her ships.
 * It contains the inner class "StartSquare" which serves as a "smart start-coordinate" for the player's ships.
 *
 * @author Elien Vissers-Similon
 * @version 1.0 02.02.2020
 */

public class Player {

    /**
     * The class StartSquare is an inner class of Player and serves as a "smart start-coordinate" of the player's ships.
     * It contains the start-coordinate, the orientation of the ship and its size.
     */
    static class StartSquare {
        private int[] coordinates;
        private boolean horizontal;
        private int size;

        StartSquare(int x, int y, boolean horizontal, int size) {
            this.coordinates = new int[]{x, y};
            this.horizontal = horizontal;
            this.size = size;
        }

        int[] getCoordinates() { return coordinates; }
        boolean isHorizontal() { return horizontal; }
        int getSize() { return size; }
    }

    private List<StartSquare> startShipList;
    private String name;
    private String color;
    private boolean isComputer;

    /**
     * Creates a new player with a name, color and status (human player or computer player).
     *
     * @param name The name of the player
     * @param color The color of the player
     * @param isComputer The status of the player (human player or computer player)
     */
    Player(String name, String color, boolean isComputer) {
        this.name = name;
        this.color = color;
        this.isComputer = isComputer;
        this.startShipList = new ArrayList<>();
    }

    public String getName() { return name; }

    public String getColor() { return color; }

    public boolean isComputer() { return isComputer; }

    /**
     * Positions a player's ship: a StartSquare is created and added to the player's List ("startShipList").
     *
     * @param currentShipCoordinates An array that contains the information about the ship that is to be placed
     */
    public void positionShip(int[] currentShipCoordinates) {
        int startX = currentShipCoordinates[0];
        int startY = currentShipCoordinates[1];
        int size = currentShipCoordinates[2];
        boolean horizontal = currentShipCoordinates[3] == 0;
        StartSquare startSquare = new StartSquare(startX, startY, horizontal, size);
        startShipList.add(startSquare);
    }

    /**
     * Translates the player's List of StartSquare coordinates (which only hold the start-coordinates) to a list which holds ALL coordinates, per ship.
     */
    public List<List<int[]>> getShipCoordinates() {
        List<List<int[]>> shipCoordinates = new ArrayList<>();
        for (StartSquare startSquare : startShipList) {
            int[] startCoordinates = new int[]{startSquare.getCoordinates()[0], startSquare.getCoordinates()[1], 1};
            List<int[]> coordinates = new ArrayList<>();
            coordinates.add(startCoordinates);
            int x = startCoordinates[0];
            int y = startCoordinates[1];
            if (startSquare.isHorizontal()) {
                switch (startSquare.getSize()) {
                    case 2: coordinates.add(new int[]{x + 1, y}); break;
                    case 3: coordinates.add(new int[]{x + 1, y}); coordinates.add(new int[]{x + 2, y}); break;
                    case 4: coordinates.add(new int[]{x + 1, y}); coordinates.add(new int[]{x + 2, y}); coordinates.add(new int[]{x + 3, y}); break;
                    case 5: coordinates.add(new int[]{x + 1, y}); coordinates.add(new int[]{x + 2, y}); coordinates.add(new int[]{x + 3, y}); coordinates.add(new int[]{x + 4, y}); break;
                }
            } else {
                switch (startSquare.getSize()) {
                    case 2: coordinates.add(new int[]{x, y + 1}); break;
                    case 3: coordinates.add(new int[]{x, y + 1}); coordinates.add(new int[]{x, y + 2}); break;
                    case 4: coordinates.add(new int[]{x, y + 1}); coordinates.add(new int[]{x, y + 2}); coordinates.add(new int[]{x, y + 3}); break;
                    case 5: coordinates.add(new int[]{x, y + 1}); coordinates.add(new int[]{x, y + 2}); coordinates.add(new int[]{x, y + 3}); coordinates.add(new int[]{x, y + 4}); break;
                }
            }
            shipCoordinates.add(coordinates);
        }
        return shipCoordinates;
    }

}
